##/**
## * Created by
## * MORRIS OMBIRO on 10/14/2017.
## * Project 1 (CSCI 1913 - Intro. to Alg.)
## * Professor James B. Moen
## */
class Random:
    def __init__(self, seed):
        self.seed = seed
    def next(self, rng): # Assuming that range is >= 0 so no need for raise exception here !
        self.seed = (((7**5)*self.seed)%((2**31)-1))
        return self.seed%rng # enables new generation of number! 
    def choose(self, characters):
        return characters[self.next(len(characters))]

class Words:
    def __init__(self, seed):
        self.__first=""
        self.__follow={}
        self.__random = Random(seed)
    
        # prez first letters - [WAJMMAJVHTPTFPBLJGHGACHCMRTWHCHRTEKJNFCRBCBOT]
    def add(self, word):
        self.word = word
        self.__first += word[0]
        for i in range(len(word)-1):
            if word[i] not in self.__follow: # python 3 doesn't have has_key()
                self.__follow.update({word[i]:word[i+1]}) 
            else: # if exists then just add value to key ! 
                self.__follow[word[i]] += word[i+1]
                
    def make(self, size): # Similarly, assuming that size is >= 0
        exmpl=""
        exmpl += self.__first[self.__random.next(len(self.__first))] # get first letter here! 
        while len(exmpl) < size: # Whatever size provided !~
            if exmpl[-1] in self.__follow: # If KeyError! 
                exmpl += self.__random.choose(self.__follow[exmpl[-1]]) 
            else:
                exmpl += self.__random.choose(self.__follow[exmpl[0]])
        return exmpl


prez = Words(101)   
prez.add('Washington')  
prez.add('Adams')  
prez.add('Jefferson')  
prez.add('Madison')  
prez.add('Monroe')  
prez.add('Adams')  
prez.add('Jackson')  
prez.add('Vanburen')  
prez.add('Harrison')  
prez.add('Tyler')  
prez.add('Polk')  
prez.add('Taylor')  
prez.add('Fillmore')  
prez.add('Pierce')  
prez.add('Buchanan')  
prez.add('Lincoln')  
prez.add('Johnson')  
prez.add('Grant')  
prez.add('Hayes')  
prez.add('Garfield')  
prez.add('Arthur')  
prez.add('Cleveland')  
prez.add('Harrison')  
prez.add('Cleveland')  
prez.add('Mckinley')  
prez.add('Roosevelt')  
prez.add('Taft')  
prez.add('Wilson')  
prez.add('Harding')  
prez.add('Coolidge')  
prez.add('Hoover')  
prez.add('Roosevelt')  
prez.add('Truman')  
prez.add('Eisenhower')  
prez.add('Kennedy')  
prez.add('Johnson')  
prez.add('Nixon')  
prez.add('Ford')  
prez.add('Carter')  
prez.add('Reagan')  
prez.add('Bush')  
prez.add('Clinton')  
prez.add('Bush')  
prez.add('Obama')  
prez.add('Trump')

for i in range(100):
    print(prez.make(7))

##########################################################OUTPUT@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    """
Grurumo
Jonsone
    Hansoro
Cldisoe
    Fovenay
Fisooos
Clnnhin
Taruson
Tayeylo
Adgeama
    Madiela
Roshnbu
Obayeay
Ponhuso
Monconc
Mcesens
Haringe
Cosovel
Lingten
Jarrixo
Jonarfi
Adinhum
Ponlele
Polnlel
    Ninasoo
Clannga
    Relayle
Hampanc
Jonhuse
Kesolin
Jolkiso
Linnrso
Einanel
Gruceve
Jendgto
Jenchix
Busonar
Tantonb
    Gaylton
Foondga
Pisoels
Adannge
Coshndi
    Haneram
Jantoel
Keltons
Adiench
    Forthis
Hangted
Aroverf
Monelto
Jonbant
Molelen
Trcerum
Einsoes
Cllixor
Adinbar
Jontera
Gaylkin
Hampoha
Grtonda
Hamshnh
Jorfish
Burdada
Winsenr
Vaylkie
Harcksh
Poowela
Bumpuma
Moonera
Obamant
Rensoso
Eiserur
Pielthn
    Wanhumo
Tylonth
Cackiso
Tarurso
Burieli
Pineven
Kevevev
Treltoo
Buchnbu
Folearu
    Ronburd
Ganbump
Hangady
    Cartoon
Tanleln
Adyliev
Tangtoo
Jedylso
Hosedgt
Adisong
Kevevev
Trenhnc
Einbama
Tylsorf
Hashnto
Triseyl
>>> 
"""
k = Words(101)
# Actual english words ! 
k.add('Bumfuzzle')
k.add('Cattywampus')
k.add('Gardyloo')
k.add('Taradiddle')
k.add('Snickersnee')
k.add('Widdershins')
k.add('Collywobbles')
k.add('Gubbins')
k.add('Abibliophobia')
k.add('Bumbershoot')
k.add('Lollygag')
k.add('Flibbertigibbet')
k.add('Mararkey')
k.add('Pandiculation')
k.add('Sialoquent')
k.add('Wabbit')

for i in range(5):
    print()
print("######################################## OWN EXAMPLE ###########################")
for i in range(20):
    if i <= 7:
        print(k.make(i+3))
    elif i > 7 and i < 15:
        print(k.make(i-5))
    else:
        print(k.make(i-10))


