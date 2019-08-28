# here's main file
import tkinter as tk
import Open
# from PIL import Image

app = Open.Application()

try:
    window = tk.Tk()
    window.title("Lucynator1.0")
    label = tk.Label(window, text = "Proszę wybrać obraz i zacząć szyć XD")
    button = tk.Button(window, text = "Wybierz Obraz", command = app.open_file)
    label.pack()
    button.pack()
    tk.mainloop()

except AttributeError:
    print("Koniec")







