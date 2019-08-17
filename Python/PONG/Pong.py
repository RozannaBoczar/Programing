import time
import pygame
import pygame.locals
import tkinter as tk
import json
from PIL import Image


class PongGame():
    def __init__(self):
        #dane są ustalane w pliku Pong.json
        json_file = open("Data/Pong.json")  #słownik danych
        self.data = json.load(json_file)
        #ścieżka do tła
        self.background = Image.open(self.data["background_image"])
        #ładujemy obrazek żeby uzyskać jego rozmiar i dopasować do niego okno
        self.width, self.height = self.background.size
        self.board = Board(self.data["background_image"], self.width, self.height)
        self.my_ball = Ball(self.width / 2, self.height / 2, self.data["ball_image"])
        self.pause = False #warunek pauzy
        self.play_with_HP = None #czy gramy z komputerem czy z drugim graczem
        self.fps_clock = pygame.time.Clock()#szybkość gry - klatki
        self.dif_level = self.data["game_level"]  #poziom trudności - szybkośc komputera
        self.player1 = Player("player1", 0, self.height / 2, self.data["player1_color"])
        self.player2 = Player("player2", self.width - 20, self.height / 2 , self.data["player2_color"], self.data["player2_speed"])
        self.count_point = Points(self.player1, self.player2, self, self.my_ball, self.data["font_color"])

    def play(self):  # gramy
        # okienko z zapytaniem o rodziaj gry
        self.starting_window()
        pygame.init()  # inizjalizacj gry/ modułów
        pygame.mixer.music.load(self.data["theme_music"])  #muzyczka
        pygame.mixer.music.play()

        if self.play_with_HP is True:
            hp = HP(self.player2, self.my_ball, self.dif_level)

        while not self.handle_events(): # "graj" lub reaguj dopóki nie będzie żadnego eventu typu pauza,reset it
            if not self.pause:
                #rysuj graczy, piłkę, punkty; ruszaj piłką licz i aktualizuj punkty graczy
                self.board.draw(self.player1, self.player2)
                self.my_ball.move_ball(self.board, self.player1, self.player2)
                self.my_ball.draw_ball(self.board)
                self.count_point.draw_on(self.board)
                self.count_point.count_points()
                self.count_point.update_score(self.width)

                if self.play_with_HP is True:
                    hp.move()
                pygame.display.update()  # zaktualizuj ekran
                self.fps_clock.tick(30)  # < 30 klatek na sekunde

    def handle_events(self):
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                pygame.quit()
                return True
            if event.type == pygame.locals.MOUSEMOTION: # sterowanie myszką(touchpadem)
                x, y = event.pos  # zczytaj pozycje i prześlij do funkcji która rusza graczem
                self.player1.move(event.type, self.height, y)
            if event.type == pygame.KEYDOWN:
                if event.key == pygame.K_r:
                    self.reset()  # reset gry
                    return True
                if self.play_with_HP is False:  # gdy nie gramy z komputerem, poruszam 2 graczem
                    if event.key == pygame.K_UP:
                        self.player2.move(event.key, self.height, 0)
                    if event.key == pygame.K_DOWN:
                        self.player2.move(event.key, self.height, 0)
                if event.key == pygame.K_p:  # pauza
                    self.pause = not self.pause
                if event.key == pygame.K_1:  # ustawianie szybkości piłki
                    self.my_ball.y_speed = 1
                    self.my_ball.x_speed = 1
                if event.key == pygame.K_2:
                    self.my_ball.y_speed = 2
                    self.my_ball.x_speed = 2
                if event.key == pygame.K_3:
                    self.my_ball.y_speed = 6
                    self.my_ball.x_speed = 6
                if event.key == pygame.K_4:
                    self.my_ball.y_speed = 8
                    self.my_ball.x_speed = 8
                if event.key == pygame.K_5:
                    self.my_ball.y_speed = 10
                    self.my_ball.x_speed = 10
                if event.key == pygame.K_6:
                    self.my_ball.y_speed = 12
                    self.my_ball.x_speed = 12
                if event.key == pygame.K_7:
                    self.my_ball.y_speed = 14
                    self.my_ball.x_speed = 14
                if event.key == pygame.K_8:
                    self.my_ball.y_speed = 16
                    self.my_ball.x_speed = 16
                if event.key == pygame.K_9:
                    self.my_ball.y_speed = 20
                    self.my_ball.x_speed = 20

    def starting_window(self):  # okienko startowe
        staring_window = tk.Tk()
        frame = tk.Frame(staring_window, bg="pink", width=400, height=300)
        label = tk.Label(frame, text="Welcome to RoziaPong!", bg="pink")
        button_play1 = tk.Button(frame, text="GAME FOR 2 PLAYERS", command=lambda: self.multi_command(staring_window))
        button_play2 = tk.Button(frame, text="PLAY WITH COMPUTER", command=lambda: self.multi_command2(staring_window))
        button_play3 = tk.Button(frame, text="EXIT", command=quit)
        label.pack()
        button_play1.pack()
        button_play2.pack()
        button_play3.pack()
        frame.pack()
        staring_window.mainloop()

    # metody do wykonywanie wielu akcji po kliknięciu danego guzika w okienku startowym

    def multi_command(self, starting_window):
        starting_window.destroy()
        self.play_with_HP = False

    def multi_command2(self, starting_window):
        starting_window.destroy()
        self.play_with_HP = True

    def reset(self):  # zamknie grę i włączy się nowa gra
        time.sleep(3)
        pygame.quit()
        new_game = PongGame()
        new_game.play()


class Board():
    def __init__(self, background, width, height):  # planaszą - wielkośc obrazka
        self.width = width
        self.height = height
        self.background = pygame.image.load(background)
        self.board = pygame.display.set_mode((self.width, self.height))
        pygame.display.set_caption('Pong Rozi')  # nazwa okienka gry

    def draw(self, *args):  # rysowanie graczy i obrazka na powierzchni
        self.board.blit(self.background, (0, 0))
        for drawable in args:
            drawable.draw_on(self.board)


class Ball():
    def __init__(self, x, y, ball):
        #self.ball = pygame.transform.rotozoom(pygame.image.load(ball), 2, 1) #niepotrzebne
        self.ball = pygame.image.load(ball)  # ładuje obrazek z podanej  ścieżki
        self.x_start = x
        self.y_start = y
        self.y_speed = 5
        self.x_speed = 5
        self.radius = 31
        self.surface = pygame.Surface([self.radius, self.radius], pygame.SRCALPHA, 32).convert_alpha()
        self.rect = self.surface.get_rect(x=x, y=y, w=self.radius*2, h=self.radius*2)  #rect - jakby pozycja
        # podane są wartości: lewy róg, góra, szerokość, wysokość - dzięki temu piłka się będzie odbijać

    def draw_ball(self, surface):  # rysowanie
        surface.board.blit(self.ball, (self.rect.x, self.rect.y))
        #pygame.draw.circle(self.surface, (0, 0, 0), (self.rect.x, self.rect.y), self.radius, 1) - tu było rysowanie
        #okręgu ale niepotrzebne

    def move_ball(self, surface, *args):  # porusza piłką - przesuwa o x/y speed - na start jest 5

        self.rect.x += self.x_speed
        self.rect.y += self.y_speed

        # metody które odbiją piłkę od ścian planszy
        if self.rect.x < 0 or self.rect.x > surface.width:
            self.bounce_x()

        if self.rect.y <= 0 or self.rect.y + self.rect.h >= surface.height:
            self.bounce_y()

        for players in args:  # gdy piłka "wykryje" gracza to sie od niego odbije, zmieni wartość x
            if self.rect.colliderect(players.rect):
                self.bounce_x()

    def bounce_y(self):
        self.y_speed *= -1

    def bounce_x(self):
        self.x_speed *= -1

    def reset(self):  # reset + odczekanie - reset po zdobyciu punktu przez któegoś z graczy
        self.rect.x = self.x_start
        self.rect.y = self.y_start
        time.sleep(1)


class Player():
    def __init__(self, name, x, y, color, max_speed = 10):
        self.points = 0
        self.name = name
        self.start_x = x
        self.start_y = y
        self.max_speed = max_speed
        self.width = 20
        self.height = 100
        self.surface = pygame.Surface([self.width, self.height], pygame.SRCALPHA, 32).convert_alpha()
        self.rect = self.surface.get_rect(x=x, y=y, w=self.width, h=self.height)
        self.surface.fill(color)  #wypełnia gracza kolorem - ustawić można w pliku konfiguracyjnym

    def draw_on(self, surface):
        surface.blit(self.surface, self.rect)

    def move(self, event, max_y, y):
        if self.rect.y + self.height >= max_y:  # ograniczenie żeby gracz się " nie schował "(nie dotyczy myszki i komputera)
            pass
        else:
            if event == pygame.K_DOWN:
                self.rect.y += self.max_speed
        if self.rect.y <= -10:
            pass
        else:
            if event == pygame.K_UP:
                self.rect.y -= self.max_speed
        if event == pygame.locals.MOUSEMOTION:
            self.rect.y = y
        if self.name == "HP":  # opcja dla komputera, podąża za piłką
            delta = y - self.rect.y
            if abs(delta) > self.max_speed:
                delta = self.max_speed if delta > 0 else -self.max_speed
            self.rect.y += delta


class Points():
    def __init__(self, player1, player2, game, ball, font_color):
        self.max = 10  # do tylu punktów gramy
        self.player1 = player1
        self.player2 = player2
        self.game = game
        self.font_color = font_color
        pygame.font.init()
        font_path = pygame.font.match_font('arial')
        self.font = pygame.font.Font(font_path, 64)
        self.ball = ball

    def update_score(self, board_width):  # jeżeli piłka wyjdzie poza długość planszy to jest punkt
        if self.ball.rect.x < 0:
            self.player2.points += 1
            self.ball.reset()
        elif self.ball.rect.x > board_width:
            self.player1.points += 1
            self.ball.reset()

    def count_points(self):
        if self.player1.points == self.max or self.player2.points == self.max:
            if self.player1.points == self.max:
                name=self.player1.name
            else:
                name=self.player2.name
            staring_window = tk.Tk()
            frame = tk.Frame(staring_window, bg="pink", width=400, height=300)
            label = tk.Label(frame, text="AND THE WINNER IS.....", bg="pink")
            label2 = tk.Label(frame, text=name, bg="pink")
            # button_play1 = tk.Button(frame, text="PLAY AGAIN", command=lambda: self.multicommand(staring_window))
            button_play2 = tk.Button(frame, text="EXIT", command=quit)
            label.pack()
            label2.pack()
            # label3.pack()
            # button_play1.pack()
            button_play2.pack()
            frame.pack()
            staring_window.mainloop()
            #pojawi się okienko z punktami i gratulacjami
        else:
            pass

    def draw_text(self, surface, text, x, y):
        text = self.font.render(text, True, self.font_color)
        rect = text.get_rect()
        rect.center = x, y  # wyśrodkuj
        surface.blit(text, rect)

    def draw_on(self, surface):  # wypisywanie punktów
        self.draw_text(surface.board, str(self.player1.name)+":"+str(self.player1.points), surface.width / 2, surface.height * 0.3)
        self.draw_text(surface.board, str(self.player2.name)+":"+str(self.player2.points), surface.width / 2, surface.height * 0.7)
        pygame.display.update()


class HP():  # komputer - szybkość można ustawić w pliku konfiguracyjnym, podąża za piłką
    def __init__(self, player, ball, dif_level):
        self.ball = ball
        self.player = player
        self.player.name = "HP"
        self.player.max_speed = dif_level

    def move(self):
        y = self.ball.rect.centery
        event = None
        max_y = 0
        self.player.move(event, max_y, y)
