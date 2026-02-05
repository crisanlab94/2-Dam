import operaciones

def test_suma_cero():
    assert operaciones.suma_enteros(0, 0) == 0

def test_suma_positivos():
    assert operaciones.suma_enteros(5, 5) == 10

def test_suma_positivos_negativos():
    assert operaciones.suma_enteros(-5, 5) == 0

def test_suma_negativos():
    assert operaciones.suma_enteros(-5, -5) == -10

def test_suma_no_entero_sumando1():
    assert operaciones.suma_enteros(5.0, 5) is None

def test_suma_no_entero_sumando2():
    assert operaciones.suma_enteros(5, "a") is None

def test_suma_no_enteros():
    assert operaciones.suma_enteros(True, "a") is None