from funciones import es_primo
def test_primo_1():
    assert es_primo(1) == False

def test_es_primo_numero_primo():
    assert es_primo(2) == True