def factorial(n):
    if type(n) is not int:
        raise TypeError("El argumento debe ser un número entero.")
        
    if n < 0:
        raise ValueError("El argumento no puede ser un número negativo.")
        
    if n == 0 or n == 1:
        return 1
    
    resultado = 1
    for i in range(2, n + 1):
        resultado *= i
        
    return resultado