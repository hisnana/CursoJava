
# Tipos de Estructuras de Control en Java

---

## 1. Estructuras Condicionales

Permiten ejecutar bloques de código dependiendo de si una condición es verdadera o falsa.

---

### 🔹 `if`

```java
if (condición) {
    // código si condición es verdadera
}
🔹 if - else
java
Copiar
Editar
if (condición) {
    // si verdadera
} else {
    // si falsa
}
🔹 if - else if - else
java
Copiar
Editar
if (cond1) {
    // bloque 1
} else if (cond2) {
    // bloque 2
} else {
    // bloque por defecto
}
🔹 switch
java
Copiar
Editar
switch (opcion) {
    case 1:
        // código
        break;
    case 2:
        // código
        break;
    default:
        // código por defecto
}
🔹 Operador Ternario ? :
Sintaxis corta para condiciones simples.

java
Copiar
Editar
resultado = (condición) ? valorSiTrue : valorSiFalse;
✅ Ejemplo:

java
Copiar
Editar
int edad = 18;
String acceso = (edad >= 18) ? "Permitido" : "Denegado";
System.out.println(acceso);  // Imprime "Permitido"
2. Estructuras Repetitivas (Bucles)
Permiten ejecutar un bloque de código varias veces.

🔹 for
java
Copiar
Editar
for (int i = 0; i < 5; i++) {
    System.out.println(i);
}
🔹 while
java
Copiar
Editar
while (condición) {
    // código mientras la condición sea verdadera
}
🔹 do-while
java
Copiar
Editar
do {
    // código que se ejecuta al menos una vez
} while (condición);
3. Control de Flujo Adicional
break → Sale del bucle o switch.

continue → Salta a la siguiente iteración del bucle.

return → Sale del método actual y opcionalmente devuelve un valor.
