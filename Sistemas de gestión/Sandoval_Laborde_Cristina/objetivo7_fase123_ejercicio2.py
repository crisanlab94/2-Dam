# Cristina Sandoval Laborde  2ºCFGS DAM
#Clase video
class Video:
    def __init__(self, titulo_video, duracion, categoria):
        self.titulo_video = titulo_video
        self.duracion = duracion
        self.categoria = categoria

    def mirar_video(self):
        print("Iniciando el video...")
        print("El video que estás viendo se titula", self.titulo_video,
              "de la categoría", self.categoria,
              "con una duración de", self.duracion, "minutos.")

    def detener_video(self):
        print("Deteniendo el video.")

#Clase Audio
class Audio:
    def __init__(self, titulo_audio, artista):
        self.titulo_audio = titulo_audio
        self.artista = artista

    def escuchar_audio(self):
        print("Iniciando el audio...")
        print("El audio que estás escuchando es", self.titulo_audio,
              "producido por el artista", self.artista)

    def detener_audio(self):
        print("Deteniendo la reproducción del audio.")

#Clase media que hereda de Video y Audio
class Media(Video, Audio):
    def __init__(self, titulo, categoria, duracion, artista):
        Video.__init__(self, titulo, duracion, categoria)
        Audio.__init__(self, titulo, artista)


# Pruebas
medio_1 = Media("Titulo 2", "comedia", 120, "Artista 2")
medio_1.escuchar_audio()
medio_1.mirar_video()
medio_1.detener_audio()
medio_1.detener_video()
