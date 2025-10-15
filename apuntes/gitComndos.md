> Luis:
# Git

## Instalación
### Windows

https://git-scm.com/downloads/win

>Next en todos los dialogos

### Linux

> Mirar en las notas en las que configuramos Ubuntu




## Configuración de usuario

> Solo necesario tras la instalación
git config --global user.name "Tu Nombre"
git config --global user.email "tu@email.com"

## Crear repositorio Git

 1. Crear un directorio
 2. En el directorio:
 git init
 3. Mostrar la configuración de repositorio
 git config -l
  

## Añadir fichero

3. Crear y introducir un texto en el readme
 echo '# testgit' >> readme.md
 4. Comprobar que se ha escrito
 more readme.md
 5. Index (add)
 git add readme.md
 

## Check repository status

6. Comprobar status
 git status
 -  Which files are staged for commit
    
- Which files have changes but are not staged
    
- Which files are untracked (new files not yet added)
    
- The current branch you’re on



## Commit

7. Commit

 commit -m
 
 git commit -m "Add initial readme"
 

## Renombrar la rama actual

8. Renombrar la rama actual a main

 git branch -M main
 
 > solo la primera vez si aún no es 'main'
 


## Configurar repositorio remoto

> Configura en el .git el usuario y el token para el repositorio remoto.
> Tener en cuenta que estos credenciales son diferentes de los de Git que se pueden almacenar en el sistema. En clase no vimos como hacerlo.

9. Solo necesario la primera vez

 git remote add origin https://<usuario>:<token>@github.com/<usuario>/<repositorio>.git
 
 


## Push

10. De - a
 git push -u origin main
 
## Clone

> En la demostración el repositorio lo creo en Ubuntu, lo subió a GitHub y lo clonó a Windows.

1. Acceder a la carpeta donde queramos clonar el repositorio
2. Clonar

 git clone https://github.com/ORG/REPO.git
 
Tener en cuenta que al clonar no asigna usuario ni token al repositorio, simplemente hace una copia de el en el local. Para asignar usuario y token mirar.
> Echar un ojo a Configurar repositorio remoto.


## Remote rm origin

Se utiliza para eliminar el remoto llamado `origin` de un repositorio Git local.

- git remote rm origin borra la URL remota (con usuario y token embebidos) del repo local, las que se anaden al hacer:
  git remote add origin https://<usuario>:<token>@github.com/<usuario>/<repositorio>.git
 - No borra las credenciales guardadas en tu sistema operativo o gestor de credenciales.
- origin es el nombre por defecto del repositorio remoto.
- Al eliminarlo, se desconecta la referencia al repositorio remoto.
- Útil cuando quieres cambiar la URL del remoto o eliminar conexiones obsoletas.
git remote rm origin


## Comprobar rama

> No obligatorio

1. Mostrar la rama en la que estas trabajando
 git branch --show-current
 
2. Mostrar todas las ramas y marca con un * la rama activa.
 git branch
 

## Crear y cambiar a una nueva rama

- Crear una nueva rama se recomienda hacer dentro de la carpeta del proyecto, es decir, dentro del repositorio Git que ya tienes.
- No es necesario ni recomendable hacer carpetas separadas para cada rama.

1. Comando moderno  (recomendado)
 git switch -c rama2
 
2. Comando antiguo pero aun valido
 git checkout -b <nombre-de-la-rama>
 
- Cada rama maneja sus commits de forma independiente.
    
- Solo cuando haces el merge, los cambios de la rama se integran en la rama principal.
    
- Después del merge, el push es necesario para actualizar el repositorio remoto.

¿Después de un git merge se necesita hacer git add y git commit antes del git push?

|Situación|Necesita git add y git commit antes de push?|
|---|---|
|Merge fast-forward|No|
|Merge con commit auto|No|
|Merge con conflictos|Sí|

## Editar y hacer add

1. Hacer modificaciones
2. Add
 1. Opcion a
   git add nombrearchivo.extension
    > Para archivos individuales
  
 2. Opcion b
   git add .
    > Para add todos los archivos modificados
  


## Diff

1. Mostrar el hash de aquel desde el que quieres comparar
 git log --oneline
 
2. Comparar 
 git diff commit1 commit2
  > En el ejemplo pone commit, pero se puede hacer entre rama, entre origen y remoto , entre directorio de trabajo con el área de staging...

> Luis:
## Merge

Es el comando que se usa para unir dos ramas diferentes en Git. Combina los cambios de una rama (por ejemplo, una rama de características) dentro de otra (por ejemplo, la rama principal `main`).

> El profesor hizo solo un ejemplo sencillo. En el, hacia una rama paralela, luego un switch al main, un merge y luego un push. No mostro un caso conflictivo

Imagina que tienes dos ramas que han evolucionado de forma independiente:
A---B---C  (main)
     \
      D---E  (rama2)

Quieres llevar los cambios de rama2 a main. Para eso, te colocas en main y haces:
git switch main
git merge rama2

Esto:

- Integra los commits D y E en la rama main.
    
- Dependiendo de los cambios, Git hará un merge automático o te pedirá resolver conflictos.

### Tipos de merge

1. Fast-forward
    
    Si la rama main no tiene commits nuevos después de donde se creó feature, Git simplemente “avanza” el puntero de main para que apunte al último commit de feature.

 Antes:
 A---B---C  (main)
      \
       D---E  (feature)
 
 Después del merge fast-forward:
 A---B---C---D---E  (main, feature)
 
2. Merge con commit (true merge)

 Si ambas ramas han avanzado, Git crea un commit de merge que une ambas historias.
 
 Antes:
 A---B---C---F  (main)
      \
       D---E  (feature)
 
 Después del merge:
         /--D---E
 A---B---C---F---M  (main)
                   /
            (feature)
  
 M es el commit de merge que tiene dos padres: F y E.
3. ¿Qué hacer si hay conflictos?

- Si hay conflictos (cambios incompatibles en los mismos archivos), Git te indicará cuáles archivos están en conflicto.
    
- Debes abrirlos, resolver manualmente los conflictos y luego:
    
 git add <archivos-resueltos>
 git commit
 
|Comando|Qué hace|
|---|---|
|`git merge <rama>`|Integra la rama indicada a la rama actual|
|`git merge --no-ff <rama>`|Fuerza la creación de un commit de merge|
|`git merge --abort`|Cancela un merge en proceso con conflictos|

---
## Fetch

> [!Warning] No visto en clase

Descarga los últimos cambios del repositorio remoto, pero no los integra en tu rama local.  
Actualiza las referencias remotas (como `origin/main`), pero tu rama local no cambia.

Ejemplo tipico

1. ### Fetch
 git fetch origin
 
 - Esto trae los cambios del remoto llamado origin.
     
 - Para qué sirve:
     
     - Ver qué cambios hay en remoto antes de integrarlos.
         
     - Mantener tu repositorio local actualizado sin afectar tu trabajo actual.
     

2. ### Cómo ver los cambios después de git fetch origin

 1. Comparar tu rama local con la rama remota
     
  Por ejemplo, si estás en main, para ver las diferencias entre tu main local y la main remota en origin:
 
   git diff main origin/main
    
 2. Ver commits nuevos en la rama remota
      
  Para listar los commits que están en la rama remota pero no en tu local:
   git log main..origin/main
      
  - Esto muestra los commits que están en origin/main pero no en main.
     
 
   Si quieres el resumen en una línea por commit:
     git log --oneline main..origin/main
     
3. ### Opcional: hacer merge o rebase para traer esos cambios a tu rama local
 
- Para fusionar los cambios:
 git merge origin/main

- Para rebasar (rebase):
 `git rebase origin/main`




