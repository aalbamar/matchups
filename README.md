# README.md
## Tabla de Contenidos

- [Descripcion](#descripcion)
- [Instalacion](#instalacion)
- [Uso](#uso)
- [Operaciones](#operaciones)

## Descripcion
Gestión de torneos.

## Instalacion
```sh
$ mvn -X -e -U -DskipTests=true clean verify install
```

## Uso
```sh
$ java -jar matchups-1.0.0.jar
```

## Operaciones
* Nuevo torneo
```sh
$ nuevo
```
* Emparejar equipos
```sh
$ emparejar
```
* 5 mejores equipos
```sh
$ top5e
```
* 5 mejores jugadores
```sh
$ top5j
```
* Mostrar datos torneo (equipo/jugadores)
```sh
$ ver
```