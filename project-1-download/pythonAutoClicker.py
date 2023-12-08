# Carson J. King
# 10/23/2023
# LTCM 3
# Learning how to code a Gui for an autoclicker from LTCM 1 and LTCM 2
# NOTE: References (https://stackoverflow.com/questions/7573031/when-i-use-update-with-tkinter-my-label-writes-another-line-instead-of-rewriti), (https://www.youtube.com/watch?v=nE1otFYO0Ss)

# Imports tk module
import tkinter as tk
# Import messagebox from tkinter
from tkinter import messagebox
# Imports time module
import time
# Imports threading module
import threading
# Imports needed objects from pynput module
from pynput.mouse import Controller, Button
from pynput.keyboard import Listener, KeyCode

# Sets the variable mouse to a controller for sending virtual mouse events to the system
mouse = Controller()

# Creates a toggle string to represent which key toggles the clicker
toggle_string = "`"
# Creates an actual "toggle key" using KeyCode and the toggle_string
toggle_key = KeyCode(char=toggle_string)

# Creation of a boolean value clicking for determining if the clicker is clicking
clicking = False
# String for outputting to user if clicker is or is not active
clicking_string = "Clicker is Not Active"
# Sleep time in between clicks of clicker (1 divided by the number of clicks wanted)
clicking_speed = .10

# Value used to indicate the program and it's separate threads should close
close = False

# Actual GUI setup within a class
class AutoClickerGUI:

    # Used to initialize values
    def __init__(self):
        
        # Creates window for GUI
        self.root = tk.Tk()
        # Sets background color of window to DimGray
        self.root.configure(background='DimGray')
        # Sets dimensions of window
        self.root.geometry("500x250")
        # Sets title for window
        self.root.title("Auto Clicker")
        
        # Creates and implements a label
        self.title_label = tk.Label(self.root, text="Configure Auto Clicker", font=('Arial', 18, 'bold'), background='DimGray')
        self.title_label.pack(padx=10, pady=10)
        
        # Creates a grid to put objects into
        self.entries_frame = tk.Frame(self.root, background='DimGray')
        # Sets columns for grid
        self.entries_frame.columnconfigure(0, weight = 1)
        self.entries_frame.columnconfigure(1, weight = 1)
        self.entries_frame.columnconfigure(2, weight = 1)
        
        # Creates button for setting click speed and places it in the grid
        self.set_button_1 = tk.Button(self.entries_frame, text="Set", font=('Arial', 18), command=self.set_click)
        self.set_button_1.grid(row=0, column=0, sticky=tk.W+tk.E, padx=5, pady=5)
        # Creates entry box for user to enter the desired click speed
        self.click_speed_entry = tk.Entry(self.entries_frame)
        self.click_speed_entry.grid(row=0, column=1, sticky=tk.W+tk.E, padx=10, pady=10)
        # Creates label to inform user of the current click speed
        self.click_label = tk.Label(self.entries_frame, text="Clicks/Sec = " + str(int(clicking_speed * 10)), font=('Arial', 18, 'bold'), background='DimGray')
        self.click_label.grid(row=0, column=2, sticky=tk.W+tk.E)
        
        # Creates button for setting toggle button of auto clicker and places it in the grid
        self.set_button_2 = tk.Button(self.entries_frame, text="Set", font=('Arial', 18), command=self.set_toggle)
        self.set_button_2.grid(row=1, column=0, sticky=tk.W+tk.E, padx=5, pady=5)
        # Creates entry box for user to enter the desired toggle key
        self.toggle_key_entry = tk.Entry(self.entries_frame)
        self.toggle_key_entry.grid(row=1, column=1, sticky=tk.W+tk.E, padx=10, pady=10)
        # Creates label to inform user of the current toggle key
        self.toggle_label = tk.Label(self.entries_frame, text="Toggle Key = " + toggle_string, font=('Arial', 18, 'bold'), background='DimGray')
        self.toggle_label.grid(row=1, column=2, sticky=tk.W+tk.E)
        
        # Stretches grid objects across the x-axis
        self.entries_frame.pack(fill='x')
        # Packs the grid into the window
        self.entries_frame.pack()
        
        # Creation of label to show user if the program is clicking or not
        self.clicking_label = tk.Label(self.root, text=clicking_string, font=('Arial', 18, 'bold'), background='DimGray')
        self.clicking_label.pack(padx=10, pady=10)
        
        # For closing of window
        self.root.protocol("WM_DELETE_WINDOW", self.on_closing)
        # For updating the window on user input
        self.updates()
        # Opens window/keeps window open
        self.root.mainloop()
    
    def updates(self):
        # So we can alter the global clicking_string inside the function
        global clicking_string
        
        # If the clicker is not active the clicking_string should express that and vice versa
        if clicking == False:
            clicking_string = "Clicker is Not Active"
        else:
            clicking_string = "Clicker is Active"
            
        # Alters clicking label to have the new clicking_string
        self.clicking_label.configure(text=clicking_string)
        # Calls this function (update()) every second and updates the window
        self.root.after(1000, self.updates)

    def set_click(self):
        # So we can alter the global clicking_speed inside the function
        global clicking_speed
        
        # If the entered click_speed is within the desired range
        if int(self.click_speed_entry.get()) <= 20 and int(self.click_speed_entry.get()) > 0:
            # Alter the label to reflect the new speed
            self.click_label.configure(text="Clicks/Sec = " + str(self.click_speed_entry.get()))
            # Change clicking_speed to the inputted clicks per second by dividing 1 by that number
            clicking_speed = 1 / int(self.click_speed_entry.get())
            # Clear the entry box
            self.click_speed_entry.delete(0, 'end')
        # Means the input was not in an acceptable range and causes a pop up informing the user of this
        else:
            messagebox.showerror(title="Error", message="Clicks/Sec is capped at 20 and cannot be negative!")
        
    def set_toggle(self):
        # So we can alter the global toggle_string inside the function
        global toggle_string
        # So we can alter the global toggle_key inside the function
        global toggle_key
        
        # As long as the input by the user is 1 key 
        if len(self.toggle_key_entry.get()) == 1:
            # The toggle_string becomes the user's input
            toggle_string = self.toggle_key_entry.get()
            # It is then used to alter the toggle_key
            toggle_key = KeyCode(char=toggle_string)
            # Alters the toggle_label to show the new key
            self.toggle_label.configure(text="Toggle Key = " + toggle_string)
            # Clears the entry box
            self.toggle_key_entry.delete(0, 'end')
        # User inputted an unacceptable value and causes a pop up informing the user of this
        else:
            messagebox.showerror(title="Error", message="You can only set the toggle key to one key!")
        
    def on_closing(self):
        # So we can alter the global close inside the function
        global close
        
        # If the user attempts to close the window through a pop up asking if they are sure
        if messagebox.askyesno(title="Quit?", message="Do you really want to quit?"):
            # Destroys window
            self.root.destroy()
            # Sets close to True signaling the whole program to end
            close = True
        
def clicker():
        # Just a variable for when the loop needs to start/end
        watcher = True
        while watcher:
            # If the auto clicker is clicking
             if clicking:
                # Click once 
                mouse.click(Button.left,1)
                # Time inbetween clicks   
             time.sleep(clicking_speed)
             # If the program needs to close
             if close == True:
                 # Stop the while loop so the thread stops
                 watcher = False
                 # Stop the listener as well
                 listener1.stop()
        
def toggle_event(key):
    # If the pressed key is the toggle key
    if key == toggle_key:
        # So we can alter the global clicking inside the function          
        global clicking
        # So we can alter the global clicking_string inside the function
        global clicking_string
        
        # Toggle the clicker
        clicking = not clicking
        
# Creates a thread for the clicking function so it may run alongside other threads
click_thread = threading.Thread(target=clicker)
# Starts the click_thread
click_thread.start()

# Creates a thread for the QUI thread so it may run alongside other threads
gui_thread = threading.Thread(target=AutoClickerGUI)
# Starts the gui_thread            
gui_thread.start()

# Creates a listener to listen for the toggle_event
with Listener(on_press=toggle_event) as listener1:
    # Starts listener
    listener1.join()
    
    
