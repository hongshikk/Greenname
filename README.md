# GreenName – 绿色聊天名称插件

**English | 中文**

---

## 简介 | Introduction

**GreenName** 是一款轻量级的 Paper 插件，允许管理员为玩家设置带有**到期时间**的绿色聊天名称。玩家可以自行使用 `/greenname on/off` 开关这一效果，所有数据基于玩家名存储，完美支持**离线服务器**。插件内置 **12 种语言** 支持，并允许管理员自定义聊天格式。

**GreenName** is a lightweight Paper plugin that allows admins to give players a **green chat name** with an **expiration date**. Players can toggle the effect on/off using `/greenname on/off`. All data is stored by **player name**, making it perfect for **offline-mode servers**. The plugin comes with **12 built-in languages** and allows admins to customize the chat format.

---

##  功能特性 | Features

- ✅ 为玩家设置绿色聊天名称（支持自定义颜色，默认绿色）
- ✅ 可配置**到期时间**（以天为单位），到期后自动失效
- ✅ 玩家可用 `/greenname on/off` 自行开关效果
- ✅ 数据基于**玩家名（小写）** 存储，适合离线服务器
- ✅ **12 种语言** 支持（简体中文、繁体中文、英语、俄语、德语、法语、西班牙语、葡萄牙语、日语、韩语、印尼语、阿拉伯语）
- ✅ 管理员可自定义**聊天格式**（支持颜色代码 `&`）
- ✅ 所有消息可通过语言文件自由修改
- ✅ 轻量、高效，无额外依赖
- ✅ 兼容 Paper 1.13 ~ 1.21+（Java 17+）

---

- ✅ Set a green chat name for players (color customizable, default green)
- ✅ Configurable **expiration time** (in days), auto-expires after expiry
- ✅ Players can toggle the effect on/off using `/greenname on/off`
- ✅ Data stored by **player name (lowercase)** – ideal for offline-mode servers
- ✅ **12 languages** supported (Chinese Simplified/Traditional, English, Russian, German, French, Spanish, Portuguese, Japanese, Korean, Indonesian, Arabic)
- ✅ Admins can customize **chat format** (supports color code `&`)
- ✅ All messages can be freely edited via language files
- ✅ Lightweight, no extra dependencies
- ✅ Compatible with Paper 1.13 ~ 1.21+ (Java 17+)

---

## 命令与权限 | Commands & Permissions

| 命令 / Command | 说明 / Description | 权限 / Permission | 默认 / Default |
|---|---|---|---|
| `/setgreen <玩家名> <天数>` | 设置玩家的绿色名称时长（天）<br>Set a player's green name duration (days) | `greennames.admin` | OP |
| `/greenname on` | 开启自己的绿色名称(仅拥有绿色名称的玩家)<br>Enable your own green name(Only players with an active greenname) | `greennames.use` | 所有人 (true) |
| `/greenname off` | 关闭自己的绿色名称(仅拥有绿色名称的玩家)<br>Disable your own green name(Only players with an active greenname) | `greennames.use` | 所有人 (true) |

---

## 配置文件 | Configuration

## `config.yml`

```yaml
# 语言设置（language name in its own script）
language: zh

# 聊天格式，& 为颜色代码，必须包含两个 %s
# Chat format, '&' is color code, must contain two %s
# 示例/Example: "&a<%s>&d %s"  → 绿色<玩家名>淡紫色 消息
chat-format: "&a<%s>&d %s"
```
## 语言文件 | Language Files

语言文件位于 `plugins/Greenname/language/` 目录，命名格式为 `messages_<lang>.yml`，支持的颜色代码为 `&0-9 &a-f &k-o &r`。管理员可以自由编辑所有提示消息。

Language files are stored in `plugins/Greenname/language/` with naming `messages_<lang>.yml`. Supports color codes `&0-9 &a-f &k-o &r`. Admins can freely edit all messages.

## 支持的语言列表 | Supported Languages

| 语言代码 | 语言名称 |
|----------|----------|
| `zh` | 简体中文 |
| `zh_tw` | 繁體中文 |
| `en` | English |
| `ru` | Русский |
| `de` | Deutsch |
| `fr` | Français |
| `es` | Español |
| `pt` | Português |
| `ja` | 日本語 |
| `ko` | 한국어 |
| `id` | Bahasa Indonesia |
| `ar` | العربية |

---

## 数据存储 | Data Storage

所有玩家数据保存在 `plugins/Greenname/data.yml` 中，结构如下：

```yaml
expiry:
  player1: 1735689600000   # 到期时间戳（毫秒）
  player2: 1735776000000
toggle:
  player1: true
  player2: false
```
## 安装 | Installation

1. 下载 `Greenname.jar`
2. 将文件放入服务器的 `plugins/` 文件夹
3. 重启服务器（或使用 `/reload`）
4. 编辑 `config.yml` 和语言文件（可选）
5. 使用 `/setgreen <玩家名> <天数>` 给玩家设置绿色名称

---

1. Download `Greenname.jar`
2. Place it in your server's `plugins/` folder
3. Restart the server (or use `/reload`)
4. Edit `config.yml` and language files (optional)
5. Use `/setgreen <player> <days>` to give a player a green name

---

## 兼容性 | Compatibility

- **服务端 | Server:** Paper 1.13 ~ 1.21+ (Spigot 可能兼容，但未测试)
- **Java:** 17 或更高 (建议 21)
- **依赖 | Dependencies:** 无 (None)

---

## 许可 | License

本项目采用 **MIT 许可证**，你可以自由使用、修改和分发。

This project is licensed under the **MIT License**. You are free to use, modify, and distribute it.

---

## 支持 | Support

如有问题或建议，请在 GitHub 提交 Issue 或联系作者。

If you have any issues or suggestions, please submit an Issue on GitHub or contact the author.

---

**感谢使用 GreenName！** | **Thanks for using GreenName!** 
