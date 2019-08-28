from tkinter import filedialog as fd
from PIL import Image
from PIL import ImageDraw


class Application:
    def __init__(self):
        self.height = None
        self.lenght = None

    def open_file(self):
        filename = fd.askopenfilename(
            filetypes=[("Obraz", "*.jpg"), ("Obraz", "*.png")])  # wywołanie okna dialogowego open file

        if filename:
            self.do_stuff(filename)

    def do_stuff(self, filename):
        filename = Image.open(filename)
        filename.show()
        self.lenght, self.height = filename.size
        d = ImageDraw.Draw(filename)
        line_color = "black"
        count1 = round(self.height/18)  # height of the "eye"
        count2 = round(self.lenght/9)  # length of the "eye"
        for i in range(0, count2):
            d.line([(self.lenght - 9*i, 0),(self.lenght - 9*i, self.height)], fill=line_color, width=1)
        for j in range(0, count2):
            d.line([(0, self.height - 18*j), (self.lenght, self.height - 18*j)], fill=line_color, width=1)
        filename.show()






