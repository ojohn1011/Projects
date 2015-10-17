package me.JakeyTheDev.Main.Game.Games;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

import me.JakeyTheDev.Main.Engine;
import me.JakeyTheDev.Main.Game.Game;
import me.JakeyTheDev.Main.Game.Settings.GameState;
import me.JakeyTheDev.Main.Game.Settings.GameType;
import net.minecraft.server.v1_8_R3.AxisAlignedBB;
import net.minecraft.server.v1_8_R3.EntityPlayer;

public class Runner extends Game
{
	private Engine _engine;

	public Runner(Engine engine)
	{
		super("Runner", 0, Arrays.asList("Blocks fall from underneath you.", "Keep running to stay alive.",
		        "Avoid falling blocks from above."));

		this._engine = engine;
	}

	private List<Location> _blocks = new ArrayList<>();

	@Override
	public void Register()
	{
		Bukkit.getPluginManager().registerEvents(this, _engine);
	}

	@Override
	public void preLoad()
	{
		GameType.setGameType(GameType.RUNNER);
	}

	@Override
	public void unRegister()
	{

		HandlerList.unregisterAll(this);
	}

	@Override
	public void unLoad()
	{
		unRegister();
	}

	@EventHandler
	public void blockChange(EntityChangeBlockEvent event)
	{
		if (event.getEntityType() == EntityType.FALLING_BLOCK)
		{
			FallingBlock fallingBlock = (FallingBlock) event.getEntity();
			if (fallingBlock.getMaterial() == Material.STAINED_CLAY)
			{
				event.setCancelled(true);
				fallingBlock.remove();
			}
		}
	}

	@EventHandler
	public void playerMove(final PlayerMoveEvent event)
	{

		if (event.isCancelled() || event.getFrom().getBlock().getLocation() == event.getFrom().getBlock().getLocation())
		    return;

		removeBlocks(event.getPlayer());
	}

	@EventHandler
	public void playerDamage(EntityDamageEvent e)
	{
		if (GameState.getGameState() == GameState.ENDING)
		{
			e.setCancelled(true);
			return;
		}

		if (e.getEntity() instanceof Player)
		{
			Player player = (Player) e.getEntity();

			if (e.getCause() == DamageCause.FALL)
			{
				e.setDamage(1);

			}
			else if (e.getCause() == DamageCause.VOID)
			{

				if (_engine.manager.getAlive().size() == 3)
				{
					_engine.manager.setPosition(3, player);

				}
				else if (_engine.manager.getAlive().size() == 2)
				{
					_engine.manager.setPosition(2, player);

					for (Player alive : _engine.manager.getAlive())
					{
						if (alive != player)
						{
							_engine.manager.setPosition(1, alive);
							_engine.manager.preFinishGame();
						}
					}
				}
				_engine.manager.removeAlive(player);
				_engine.manager.addSpectator(player);
			}
		}
	}

	public void removeBlocks(Player player)
	{
		if (GameState.getGameState() != GameState.IN_PROGRESS || _engine.manager.getSpectators().contains(player))
		    return;

		for (final Block block : getBlocksBelow(player))
		{
			if (block.getType() == Material.AIR || block.getType() == null || block == null) return;

			if (_blocks.contains(block.getLocation())) return;

			_blocks.add(block.getLocation());

			block.setType(Material.STAINED_CLAY);
			block.setData((byte) new Random().nextInt(15));

			new BukkitRunnable()
			{
				@Override
				public void run()
				{
					block.setType(Material.AIR);
					final FallingBlock fallingBlock = block.getWorld()
			                .spawnFallingBlock(block.getLocation().add(0, 0, 0), Material.STAINED_CLAY, (byte) 14);

					new BukkitRunnable()
					{
						@Override
						public void run()
						{
							fallingBlock.remove();
							_blocks.remove(block.getLocation());
						}
					}.runTaskLater(_engine, 50L);
				}
			}.runTaskLater(_engine, 6L);
		}
	}

	public ArrayList<Block> getBlocksBelow(Player player)
	{
		ArrayList<Block> blocksBelow = new ArrayList<Block>();
		EntityPlayer entityPlayer = ((CraftPlayer) player).getHandle();
		AxisAlignedBB boundingBox = entityPlayer.getBoundingBox();
		World world = player.getWorld();
		double yBelow;
		if (player.isOnGround())
		{
			yBelow = player.getLocation().getY() - 0.0001;
		}
		else
		{
			yBelow = player.getLocation().getY() - 1.5001;
		}
		Block northEast = new Location(world, boundingBox.d, yBelow, boundingBox.c).getBlock();
		Block northWest = new Location(world, boundingBox.a, yBelow, boundingBox.c).getBlock();
		Block southEast = new Location(world, boundingBox.d, yBelow, boundingBox.f).getBlock();
		Block southWest = new Location(world, boundingBox.a, yBelow, boundingBox.f).getBlock();
		Block[] blocks =
		{ northEast, northWest, southEast, southWest };
		for (Block block : blocks)
		{
			if (!blocksBelow.isEmpty())
			{
				boolean duplicateExists = false;
				for (int i = 0; i < blocksBelow.size(); i++)
				{
					if (blocksBelow.get(i).equals(block))
					{
						duplicateExists = true;
					}
				}
				if (!duplicateExists)
				{
					blocksBelow.add(block);
				}
			}
			else
			{
				blocksBelow.add(block);
			}
		}
		return blocksBelow;
	}
}
