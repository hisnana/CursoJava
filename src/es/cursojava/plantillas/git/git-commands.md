# 🧠 Git — Chuleta básica (CLI)

> Consejos rápidos: usa `git status` y `git log --oneline --graph` a menudo. Evita `push --force` salvo que sepas lo que haces (mejor `--force-with-lease`).

## 👤 Configuración (identidad)

```bash
git config --global user.name  "tu-usuario"
git config --global user.email "ID+usuario@users.noreply.github.com"   # email noreply GitHub
git config --local  user.email "ID+usuario@users.noreply.github.com"   # en este repo
git config --show-origin user.email
```

## 📦 Crear / clonar / remoto

```bash
git init                                  # inicializa repo
git clone <url>                            # clona repo
git remote -v                              # ver remotos
git remote add origin <url>                # añade remoto
git remote set-url origin <url>            # cambia URL del remoto
```

## 🔎 Estado y diferencias

```bash
git status
git diff                                   # cambios no staged
git diff --staged                          # cambios staged
git log --oneline --graph --decorate       # historial gráfico corto
git show <commit>                          # ver un commit
```

## ➕ Añadir y confirmar

```bash
git add .                                  # añade todo al staging
git add <ruta>                             # añade selectivo
git commit -m "mensaje"                    # crea commit
git commit --amend                         # reescribe último (edita msg)
git commit --amend --no-edit               # reescribe (mismo msg)
```

## 🌿 Ramas

```bash
git branch                                  # lista ramas
git branch -M main                          # renombra rama actual a main
git switch -c feature/x                     # crea y cambia a rama
git switch main                             # cambia de rama (nuevo)
git checkout main                           # cambia de rama (legacy)
git branch -d feature/x                     # borra rama fusionada
git branch -D feature/x                     # borra forzado
```

## 🔄 Sincronizar con remoto

```bash
git fetch origin                            # trae refs remotas
git pull --ff-only                          # actualiza sin commits de merge
git pull --rebase                           # reescribe como historia lineal
git push                                    # sube rama actual
git push -u origin <rama>                   # primera vez (set upstream)
```

## 🧰 Stash (guardar cambios sin commitear)

```bash
git stash push -m "msg"                     # guarda cambios y limpia WD
git stash list                              # ver stashes
git stash pop                               # aplica y elimina de la pila
git stash apply stash@{n}                   # aplica uno concreto
```

## 🏷️ Tags (versiones)

```bash
git tag -a v1.0.0 -m "release 1.0.0"        # crea tag anotado
git tag                                     # lista tags
git push origin v1.0.0                      # sube un tag
git push origin --tags                      # sube todos (con cuidado)
git tag -d v1.0.0                           # borra tag local
git push origin :refs/tags/v1.0.0           # borra tag remoto
```

## ⏪ Deshacer (sin perder trabajo)

```bash
git restore <ruta>                          # descarta cambios en archivo
git restore --staged <ruta>                 # saca de staged (equiv. reset)
git revert <commit>                         # crea commit inverso (seguro)
```

## 🔁 Reset (mover HEAD) — ⚠️ con cuidado

| Modo      | Qué hace                                           | Uso típico           |
| --------- | -------------------------------------------------- | -------------------- |
| `--soft`  | mueve HEAD; **conserva** staged y WD               | rehacer commits en 1 |
| `--mixed` | mueve HEAD; **unstage**; mantiene WD (por defecto) | sacar de staged      |
| `--hard`  | mueve HEAD; **borra** staged y WD                  | limpiar por completo |

```bash
git reset --soft HEAD~1                      # vuelve 1 commit, conserva cambios
git reset HEAD <ruta>                        # saca archivos de staged
git reset --hard                             # deja el repo limpio al último commit
```

## 🆘 Reflog (recuperaciones)

```bash
git reflog                                  # histórico de movimientos de HEAD
git checkout <sha>                           # navegar a estados antiguos
# para recuperar algo perdido tras un reset duro: busca el sha en reflog y crea rama
git branch rescue/<fecha> <sha>
```

## 🤝 Merge y rebase (flujo básico)

```bash
# Fusionar main en tu rama (resolver conflictos si los hay)
git switch feature/x
git fetch origin
git merge origin/main
# o historia lineal:
git rebase origin/main
```

## 🧩 Cherry-pick (traer un commit suelto)

```bash
git cherry-pick <sha>
```

## 🧼 Limpiar archivos sin seguimiento (⚠️ destructivo)

```bash
git clean -fd                               # borra untracked y carpetas
git clean -fd -n                            # simulación
```

## 🙈 .gitignore (ignorar archivos)

```
# ejemplo
/target/
*.class
*.log
.DS_Store
```

---

## 🛡️ Privacidad GitHub (GH007: email privado)

* Detectar commits con email real:

```bash
git fetch origin
git log origin/<rama>..HEAD --pretty="%h | %an <%ae> | %cn <%ce>"
```

* Arreglo rápido (recommit en 1 commit con noreply):

```bash
git reset --soft origin/<rama>
git commit -m "recommit con noreply"
git push --force-with-lease
```

* Arreglo manteniendo historial:

```bash
git rebase -i origin/<rama>    # marca 'edit'
git commit --amend --reset-author --no-edit
git rebase --continue
git push --force-with-lease
```

## 🧭 Flujo mínimo de trabajo (diario)

```bash
git switch <rama>                 # o crea: git switch -c <rama>
git pull --rebase                 # actualiza
# ... cambios ...
git add .
git commit -m "mensaje"
git push                          # primera vez: git push -u origin <rama>
```
