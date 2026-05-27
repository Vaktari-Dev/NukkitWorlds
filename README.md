# NukkitWorlds

![Version](https://img.shields.io/badge/version-1.0.0-brightgreen)
![Nukkit](https://img.shields.io/badge/Nukkit-PM1E%20%7C%20MOT%20%7C%20Lumi-blue)
![Java](https://img.shields.io/badge/Java-17%2B-orange)
![License](https://img.shields.io/badge/license-MIT-green)

**NukkitWorlds** — лёгкий и производительный плагин для управления поведением миров на серверах **Nukkit-PM1E**, **Nukkit-MOT** и **LumiCore**. Позволяет гибко настраивать запрет спавна мобов, опадения листвы, распространения огня и пожаров от молний для каждого мира отдельно.

---

## Возможности

- **Запрет спавна мобов** — отключите спавн враждебных мобов в выбранных мирах
- **Защита листвы** — остановите decay листьев в декоративных постройках
- **Контроль огня** — запретите распространение огня и поджигание лавой
- **Защита от молний** — предотвратите пожары от ударов молний
- **Гибкая настройка** — каждый параметр отдельно для каждого мира
- **Hot reload** — перезагрузка конфигурации без перезапуска сервера
- **Автоопределение ядра** — автоматически определяет PM1E, MOT или LumiCore
- **Минимальное потребление** — lightweight, без лишних зависимостей

---

## Поддерживаемые ядра

| Ядро | Статус |
|------|--------|
| Nukkit-PM1E | ✅ Полная поддержка |
| Nukkit-MOT | ✅ Полная поддержка |
| LumiCore | ✅ Полная поддержка |

---

## Установка

1. Скачайте последний релиз `NukkitWorlds.jar` со страницы [releases](https://github.com/Vaktari-Dev/NukkitWorlds/releases)
2. Поместите `.jar` файл в папку `plugins/` вашего сервера
3. Перезапустите сервер
4. Настройте `plugins/NukkitWorlds/config.yml` под себя
5. Готово!

### Требования

- **Java** 17 или выше
- **Nukkit-PM1E** / **Nukkit-MOT** / **LumiCore** latest

---

## Команды

| Команда | Описание | Permission |
|---------|----------|------------|
| `/nworlds reload` | Перезагрузить конфигурацию | `nukkitworlds.reload` |
| `/nworlds info` | Показать конфигурацию миров | `nukkitworlds.info` |

---

## Permissions

| Permission | Описание | Default |
|------------|----------|---------|
| `nukkitworlds.admin` | Доступ ко всем командам | `op` |
| `nukkitworlds.reload` | Перезагрузка конфигурации | `op` |
| `nukkitworlds.info` | Просмотр конфигурации миров | `op` |

---

## Конфигурация

### config.yml

```yaml
debug: false

disabled-mob-spawn:
  - "world"
  - "nether"
  - "the_end"

disabled-leaf-decay:
  - "world"

disabled-fire:
  - "world"

disabled-lightning-fire:
  - "world"
  - "nether"
```

### messages.yml

```yaml
prefix: "&8[&3NukkitWorlds&8] &r"

no-permission: "{prefix}&cУ вас нет прав на использование этой команды."
reload-success: "{prefix}&aКонфигурация успешно перезагружена."
info-header: "{prefix}&b=== &3Конфигурация миров &b==="
info-entry: " &7- &f{world}&8: &7spawn={spawn}&8, decay={decay}&8, fire={fire}&8, lightning={lightning}"
info-footer: "{prefix}&b=== &3{count} &bмиров всего ==="
```

Поддерживаются цветовые коды `&0-&f`, `&l`, `&m`, `&n`, `&o`, `&r`.

---

## Примеры использования

**Защита спавна от мобов:**
```yaml
disabled-mob-spawn:
  - "world"
  - "spawn"
```

**Защита декоративного мира от опадения листвы:**
```yaml
disabled-leaf-decay:
  - "build"
  - "park"
```

**Полная защита от пожаров в мире:**
```yaml
disabled-fire:
  - "lobby"
disabled-lightning-fire:
  - "lobby"
```

---

## Журнал изменений

### 1.0.0
- Первый стабильный релиз
- Запрет спавна мобов в выбранных мирах
- Запрет опадения листвы
- Запрет распространения огня
- Запрет пожаров от молний
- Hot reload конфигурации
- Команда `/nworlds info`
- Автоопределение ядра (PM1E/MOT/Lumi)
- Полная конфигурация через YAML

---

## Сборка из исходников

```bash
git clone https://github.com/Vaktari-Dev/NukkitWorlds.git
cd NukkitWorlds
mvn clean package
```

Скомпилированный `.jar` появится в папке `target/`.

---

## Разработчик

**Vaktari-Dev**

- GitHub: [@Vaktari-Dev](https://github.com/Vaktari-Dev)

---

## Лицензия

Этот проект распространяется под лицензией MIT.
