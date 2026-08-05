# HungryFlight

A lightweight Minecraft plugin that allows players to fly while managing flight costs through hunger.

HungryFlight adds a survival-friendly flight system where players consume hunger while flying. Flight can automatically disable when a player's hunger becomes too low, with optional costs for interactions such as breaking blocks, placing blocks, attacking entities, and taking damage.

## Features

- Toggle player flight with `/fly`
- Toggle another player's flight with `/fly <player>`
- Hunger-based flight costs
- Automatic flight disable when hunger is too low
- Configurable flight cost timer
- Configurable interaction-based hunger costs
  - Block breaking
  - Block placing
  - Attacking entities
  - Taking damage
- Permission-based no hunger flight
- Customizable messages using MiniMessage
- Actionbar flight status display
- Lightweight and optimized

## Commands

| Command | Description | Permission |
| --- | --- | --- |
| `/fly` | Toggle your flight | `hungryflight.fly` |
| `/fly <player>` | Toggle another player's flight | `hungryflight.fly.other` |
| `/flyadmin reload` | Reload the configuration | `hungryflight.admin.reload` |

## Permissions

| Permission | Description | Default |
| --- | --- | --- |
| `hungryflight.fly` | Allows using `/fly` | Everyone |
| `hungryflight.fly.other` | Allows toggling another player's flight | OP |
| `hungryflight.nohunger` | Allows flying without hunger costs | OP |
| `hungryflight.admin.reload` | Allows reloading the plugin | OP |
| `hungryflight.groups.second` | Access to the second hunger cost group | OP |

## Configuration

HungryFlight uses a YAML configuration file.

Messages support MiniMessage formatting:

```yaml
fly-on: "<green>Flight enabled!"
fly-off: "<red>Flight disabled!"
