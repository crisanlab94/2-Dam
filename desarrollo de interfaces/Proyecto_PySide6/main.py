from PySide6.QtWidgets import QApplication, QPushButton
import sys

app = QApplication(sys.argv)
button = QPushButton("Hola PySide6")
button.show()
app.exec()