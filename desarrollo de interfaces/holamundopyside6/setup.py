import pathlib
from setuptools import setup
# Directorio raíz del proyecto
directorio_raiz = pathlib.Path(__file__).parent
# Leer README.md
README = (directorio_raiz / "README.md").read_text(encoding="utf-8")
setup(
name="holamundopyside6-crisanlab",
version="0.0.2",
description="Hola mundo con PySide6",
long_description=README,
long_description_content_type="text/markdown",
author="Nombre Apellidos",
author_email="correo@ejemplo.com",
license="MIT",
packages=["holamundopyside6"],
include_package_data=True,
install_requires=[],
entry_points={
"console_scripts": [
"holamundopyside6=holamundopyside6.__main__:main",
]
},
)