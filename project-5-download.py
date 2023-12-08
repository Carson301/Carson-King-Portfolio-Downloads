# Carson J. King
# 12/3/23
# Armwrestling Demo Machine

#Imports needed modules
import RPi.GPIO as GPIO
import time
import threading
import tkinter as tk

#Sets universal GPIO pins for direction and step of motors
MOTOR_1_DIR = 15
MOTOR_1_STEP = 18

MOTOR_2_DIR = 23
MOTOR_2_STEP = 24

MOTOR_3_DIR = 21
MOTOR_3_STEP = 20

# GPIO setups
GPIO.setmode(GPIO.BCM)
GPIO.setwarnings(False)
GPIO.setup(MOTOR_1_DIR, GPIO.OUT)
GPIO.setup(MOTOR_1_STEP, GPIO.OUT)
GPIO.setup(MOTOR_2_DIR, GPIO.OUT)
GPIO.setup(MOTOR_2_STEP, GPIO.OUT)
GPIO.setup(MOTOR_3_DIR, GPIO.OUT)
GPIO.setup(MOTOR_3_STEP, GPIO.OUT)

#Sets number of steps needed for arm to go from start to pin pad
CENTER_TO_PIN = 30

# Variables for determining both hand and direction of motor spins
hand = 1
clockwise = GPIO.LOW
counter_clockwise = GPIO.HIGH

#String used to update window when hand changes
hand_string = "Right"
#Boolean used to see if machine is currently pinning
pinning = False
#String used to update window when pin is in progress
default_string = "Not Pinning"
#Boolean used to see if the pin button was pressed
default = False

#Boolean used to see if machine is currently toprolling
toprolling = False
#String used to update window when toproll is in progress
toproll_string = "Not Toprolling"
#Boolean used to see if the toproll button was pressed
toproll = False

#Boolean used to see if machine is currently hooking
hooking = False
#String used to update window when hook is in progress
hooking_string = "Not Hooking"
#Boolean used to see if the hook button was pressed
hook = False

#Boolean used to see if machine is currently pressing
pressing = False
#String used to update window when press is in progress
pressing_string = "Not Pressing"
#Boolean used to see if the press button was pressed
press = False

#Boolean used to see if the program should end/Ends some threads using loops
close = False

#Boolean used to see if the machine is active in any movement
active = False

class ArmWrestlingMachine:
        
    def __init__(self):
        #Creates window 
        self.root = tk.Tk()
        #Sets dimensions of window
        self.root.geometry("500x500")
        #Gives the window a title
        self.root.title("Arm Wrestling Demo")
            
        #Gives another title for the window, but inside the window
        self.title_label = tk.Label(self.root, text="Arm Wrestling Controller", font=('Arial', 18, 'bold'))
        self.title_label.pack(padx=10, pady=10)

        #Creates a grid layout that can have objects placed inside it
        self.entries_frame = tk.Frame(self.root)
            
        #Creates 4 columns
        self.entries_frame.columnconfigure(0, weight = 1)
        self.entries_frame.columnconfigure(1, weight = 1)
        self.entries_frame.columnconfigure(2, weight = 1)
        self.entries_frame.columnconfigure(3, weight = 1)
            
        #Creation of button for user to set the hand to right hand
        #Calls set_right method when clicked
        self.right_button = tk.Button(self.entries_frame, text="Right", font=('Arial', 18), command=self.set_right)
        self.right_button.grid(row=0, column=0, sticky=tk.W+tk.E, padx=5, pady=5)
            
        #Creation of button for user to set the hand to left hand
        #Calls set_left method when clicked
        self.left_button = tk.Button(self.entries_frame, text="Left", font=('Arial', 18), command=self.set_left)
        self.left_button.grid(row=0, column=1, sticky=tk.W+tk.E, padx=5, pady=5)
            
        #Creation of label to display what hand the machine is currently on
        self.hand_label = tk.Label(self.entries_frame, text="Set to " + hand_string + " Hand", font=('Arial', 18, 'bold'))
        self.hand_label.grid(row=0, column=3, sticky=tk.W+tk.E, padx=5, pady=5)

        #Creation of button for user to start machine pinning
        #Calls pin_arm method when clicked
        self.default_button = tk.Button(self.entries_frame, text="Default", font=('Arial', 18), command=self.default_arm)
        self.default_button.grid(row=1, column=0, sticky=tk.W+tk.E, padx=5, pady=5)

        #Creation of label for if the machine is currently pinning
        self.default_label = tk.Label(self.entries_frame, text=default_string, font=('Arial', 18, 'bold'))
        self.default_label.grid(row=1, column=3, sticky=tk.W+tk.E, padx=5, pady=5)
            
        #Creation of button for user to start machine toproll
        #Calls toproll_arm method when clicked
        self.toproll_button = tk.Button(self.entries_frame, text="Toproll", font=('Arial', 18), command=self.toproll_arm)
        self.toproll_button.grid(row=2, column=0, sticky=tk.W+tk.E, padx=5, pady=5)

        #Creation of label for if the machine is currently toprolling
        self.toproll_label = tk.Label(self.entries_frame, text=toproll_string, font=('Arial', 18, 'bold'))
        self.toproll_label.grid(row=2, column=3, sticky=tk.W+tk.E, padx=5, pady=5)
            
        #Creation of button for user to start machine hooking
        #Calls hooking_arm method when clicked
        self.hooking_button = tk.Button(self.entries_frame, text="Hooking", font=('Arial', 18), command=self.hooking_arm)
        self.hooking_button.grid(row=3, column=0, sticky=tk.W+tk.E, padx=5, pady=5)

        #Creation of label for if the machine is currently hooking
        self.hooking_label = tk.Label(self.entries_frame, text=hooking_string, font=('Arial', 18, 'bold'))
        self.hooking_label.grid(row=3, column=3, sticky=tk.W+tk.E, padx=5, pady=5)
            
        #Creation of button for user to start machine pressing
        #Calls pressing_arm method when clicked
        self.pressing_button = tk.Button(self.entries_frame, text="Pressing", font=('Arial', 18), command=self.pressing_arm)
        self.pressing_button.grid(row=4, column=0, sticky=tk.W+tk.E, padx=5, pady=5)

        #Creation of label for if the machine is currently pressing
        self.pressing_label = tk.Label(self.entries_frame, text=pressing_string, font=('Arial', 18, 'bold'))
        self.pressing_label.grid(row=4, column=3, sticky=tk.W+tk.E, padx=5, pady=5)
            
        #Packs entries frame so objects fill the x axis
        self.entries_frame.pack(fill='x')
            
        #Calls on_closing when window is closed
        self.root.protocol("WM_DELETE_WINDOW", self.on_closing)
            
        #Calls updates function to update window periodically
        self.updates()
            
        #Keeps window open
        self.root.mainloop()

    def updates(self):
            
        #So we may alter any displayed strings in the window
        global default_string
        global toproll_string
        global hooking_string
        global pressing_string
        global clockwise
        global counter_clockwise
        
        #If we are right handed clockwise changed
        if hand == 1:
            clockwise = GPIO.LOW
            counter_clockwise = GPIO.HIGH
        #Otherwise we are left and change accordingly
        else:
            clockwise = GPIO.HIGH
            counter_clockwise = GPIO.LOW
            
        #If pinning is False the string should represent that
        if pinning == False:
            default_string = "Not Pinning"
        else:
            default_string = "Pinning"  
        #Alters pin_label, updating it
        self.default_label.configure(text=default_string)
            
        #If toprolling is False the string should represent that
        if toprolling == False:
            toproll_string = "Not Toprolling"
        else:
            toproll_string = "Toprolling"
        #Alters toproll_label
        self.toproll_label.configure(text=toproll_string)
            
        #If hooking is False the string should represent that
        if hooking == False:
            hooking_string = "Not Hooking"
        else:
            hooking_string = "Hooking"
        #Alters hooking_label
        self.hooking_label.configure(text=hooking_string)
            
        #If pressing is False the string should represent that
        if pressing == False:
            pressing_string = "Not Pressing"
        else:
            pressing_string = "Pressing"
        #Alters pressing_label
        self.pressing_label.configure(text=pressing_string)
            
        #Alters hand_label, updating it
        self.hand_label.configure(text="Set to " + hand_string + " Hand")
            
        #Calls itself so that it is always updating the window
        self.root.after(1000, self.updates)
                    


    def set_right(self):
        #If the machine is not currently active we are safe to change the hand
        if active == False:
            #So we may alter hand and hand__string
            global hand
            global hand_string
            hand = 1
            hand_string = "Right"
            
    def set_left(self):
        #If the machine is not currently active we are safe to change the hand
        if active == False:
            #So we may alter hand and hand__string
            global hand
            global hand_string
            hand = 0
            hand_string = "Left"
        
    def default_arm(self):
        #So we may alter pin
        global default
        #If not currently active we are safe to tell the machine to pin
        if active == False:
            default = True
                
    def toproll_arm(self):
        #So we may alter toproll
        global toproll
        #If not currently active we are safe to tell the machine to pin
        if active == False:
            toproll = True
        
    def hooking_arm(self):
        #So we may alter hook
        global hook
        #If not currently active we are safe to tell the machine to pin
        if active == False:
            hook = True
                
    def pressing_arm(self):
        #So we may alter press
        global press
        #If not currently active we are safe to tell the machine to pin
        if active == False:
            press = True
            
    def on_closing(self):
        #So we may alter close
        global close
        #So other methods that loop may know to end
        close = True
        #Destroy window
        self.root.destroy()
            
def spin_63_degrees(direction_pin, step_pin, direction):
    #Sets direction of spin clockwise or counter_clockwise
    GPIO.output(direction_pin, direction)
    #Does 35 steps in a given direction for a given motor
    for i in range(CENTER_TO_PIN):
        #Take step
        GPIO.output(step_pin, GPIO.HIGH)
        time.sleep(.02)
        #Pause
        GPIO.output(step_pin, GPIO.LOW)
        time.sleep(.01)
    time.sleep(1)
    
def spin_90_degrees(direction_pin, step_pin, direction):
    #Sets direction of spin clockwise or counter_clockwise
    GPIO.output(direction_pin, direction)
    #Does 35 steps in a given direction for a given motor
    for i in range(50):
        #Take step
        GPIO.output(step_pin, GPIO.HIGH)
        time.sleep(.03)
        #Pause
        GPIO.output(step_pin, GPIO.LOW)
        time.sleep(.015)
    time.sleep(1)
    
def wave(direction_pin, step_pin):
    #Sets direction of spin clockwise or counter_clockwise for wave
    GPIO.output(direction_pin, GPIO.LOW)
    for i in range(20):
        #Take step
        GPIO.output(step_pin, GPIO.HIGH)
        time.sleep(.03)
        #Pause
        GPIO.output(step_pin, GPIO.LOW)
        time.sleep(.015)
    GPIO.output(direction_pin, GPIO.HIGH)
    for i in range(40):
        #Take step
        GPIO.output(step_pin, GPIO.HIGH)
        time.sleep(.03)
        #Pause
        GPIO.output(step_pin, GPIO.LOW)
        time.sleep(.015)
    GPIO.output(direction_pin, GPIO.LOW)
    for i in range(40):
        #Take step
        GPIO.output(step_pin, GPIO.HIGH)
        time.sleep(.03)
        #Pause
        GPIO.output(step_pin, GPIO.LOW)
        time.sleep(.015)
    GPIO.output(direction_pin, GPIO.HIGH)
    for i in range(20):
        #Take step
        GPIO.output(step_pin, GPIO.HIGH)
        time.sleep(.03)
        #Pause
        GPIO.output(step_pin, GPIO.LOW)
        time.sleep(.015)
            
def default_demo():
    #To alter global variables
    global active
    global default
    global pinning
    global toproll
    global toprolling
    global hook
    global hooking
    global press
    global pressing
    time.sleep(10)
    if active == False:
        active = True
        print("Waving Begin")
        spin_90_degrees(MOTOR_2_DIR, MOTOR_2_STEP, counter_clockwise)
        wave(MOTOR_1_DIR, MOTOR_1_STEP)
        spin_90_degrees(MOTOR_2_DIR, MOTOR_2_STEP, clockwise)
        print("Waving End")
        active = False
    #To watch if the loop should end since this is a separate thread
    watcher = True
    while watcher:
        #If the default was pressed the machine should pin
        if default == True:
            #Machine active
            active = True
            #Pinning occuring
            pinning = True
            print("Default Demo Begin")
            time.sleep(1)
            #Pin
            spin_63_degrees(MOTOR_1_DIR, MOTOR_1_STEP, clockwise)
            #Reset Pin
            spin_63_degrees(MOTOR_1_DIR, MOTOR_1_STEP, counter_clockwise)
            print("Default Demo End")
            #Default is done
            default = False
            active = False
            pinning = False
        #If the toproll was pressed the machine should pin
        if toproll == True:
            #Machine is active
            active = True
            #Toprolling occuring
            toprolling = True
            print("Toproll Demo Begin")
            time.sleep(1)
            #Cup
            spin_63_degrees(MOTOR_3_DIR, MOTOR_3_STEP, clockwise)
            #Pronate
            spin_63_degrees(MOTOR_2_DIR, MOTOR_2_STEP, clockwise)
            #Pin
            spin_63_degrees(MOTOR_1_DIR, MOTOR_1_STEP, clockwise)
            #Reset Pin
            spin_63_degrees(MOTOR_1_DIR, MOTOR_1_STEP, counter_clockwise)
            #Reset Pronation
            spin_63_degrees(MOTOR_2_DIR, MOTOR_2_STEP, counter_clockwise)
            #Reset Cupp
            spin_63_degrees(MOTOR_3_DIR, MOTOR_3_STEP, counter_clockwise)
            print("Toproll Demo End")
            #Toproll is done
            toproll = False
            active = False
            toprolling = False
        #If the hook was pressed the machine should hook
        if hook == True:
            #Machine is active
            active = True
            #Hooking occuring
            hooking = True
            print("Hook Demo Begin")
            time.sleep(1)
            #Cup
            spin_63_degrees(MOTOR_3_DIR, MOTOR_3_STEP, clockwise)
            #Supinate
            spin_63_degrees(MOTOR_2_DIR, MOTOR_2_STEP, counter_clockwise)
            #Pin
            spin_63_degrees(MOTOR_1_DIR, MOTOR_1_STEP, clockwise)
            #Reset Pin
            spin_63_degrees(MOTOR_1_DIR, MOTOR_1_STEP, counter_clockwise)
            #Reset Supination
            spin_63_degrees(MOTOR_2_DIR, MOTOR_2_STEP, clockwise)
            #Reset Cup
            spin_63_degrees(MOTOR_3_DIR, MOTOR_3_STEP, counter_clockwise)
            print("Hook Demo End")
            #Hook is done
            hook = False
            active = False
            hooking = False
            
        #If the press was pressed the machine should press
        if press == True:
            #Machine is active
            active = True
            #Pressing occuring
            pressing = True
            print("Press Demo Begin")
            time.sleep(1)
            #Supinate
            spin_63_degrees(MOTOR_2_DIR, MOTOR_2_STEP, counter_clockwise)
            #Pin
            spin_63_degrees(MOTOR_1_DIR, MOTOR_1_STEP, clockwise)
            #Reset Pin
            spin_63_degrees(MOTOR_1_DIR, MOTOR_1_STEP, counter_clockwise)
            #Reset Supination
            spin_63_degrees(MOTOR_2_DIR, MOTOR_2_STEP, clockwise)
            print("Press Demo End")
            #Press is done
            press = False
            active = False
            pressing = False
        #If close is True the while loop should end and program should "close"
        if close == True:
            watcher = False
                
#Creates a thread so that pinningThem is always listening to if it should pin
default_thread = threading.Thread(target=default_demo)
#Start default thread
default_thread.start()

#Creates a thread for the ArmWrestlingMachine class so that it is runnning
am_thread = threading.Thread(target=ArmWrestlingMachine)
#Start Machine thread/GUI
am_thread.start()
