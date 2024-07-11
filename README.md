MARS ROVERS

You’re part of the team that explores Mars by sending remotely controlled vehicles to the surface of the planet. Develop an API that translates the commands sent from earth to instructions that are understood by the rover.

Requirements
A squad of robotic rovers are to be landed by NASA on a plateau on Mars.
This plateau, which is curiously rectangular, must be navigated by the rovers so that their on board cameras can get a complete view of the surrounding terrain to send back to Earth.
A rover's position is represented by a combination of an x and y co-ordinates and a letter representing one of the four cardinal compass points. The plateau is divided up into a grid to simplify navigation. An example position might be 0, 0, N, which means the rover is in the bottom left corner and facing North.
In order to control a rover, NASA sends a simple string of letters. The possible letters are 'L', 'R' and 'M'. 'L' and 'R' makes the rover spin 90 degrees left or right respectively, without moving from its current spot.
'M' means move forward one grid point, and maintain the same heading.
Assume that the square directly North from (x, y) is (x, y+1).

Input:
The first line of input is the upper-right coordinates of the plateau, the lower-left coordinates are assumed to be 0,0.
The rest of the input is information pertaining to the rovers that have been deployed. Each rover has two lines of input. The first line gives the rover's position, and the second line is a series of instructions telling the rover how to explore the plateau.
The position is made up of two integers and a letter separated by spaces, corresponding to the x and y co-ordinates and the rover's orientation.
Each rover will be finished sequentially, which means that the second rover won't start to move until the first one has finished moving.
Implement wrapping at edges
Implement obstacle detection before each move to a new square. If a given sequence of commands encounters an obstacle, the rover moves up to the last possible point, aborts the sequence and reports the obstacle.

Output:
The output for each rover should be its final co-ordinates and heading.
Test Input:
5 5
1 2 N
LMLMLMLMM
3 3 E
MMRMMRMRRM
Expected Output:
1 3 N
5 1 E

Implementation:
I first implemented the Java methods that help in adding rover and executing all the rovers' instructions. On rereading the kata, thought it may be a good idea to add the rest end point
1. Create plateau endpoint
   a. Option to create a default plateau (0,0) - (0,10) -----  post /plateau/default
   b. Option to create a plateau with default min coordinates i.e (0,0) and user provided max coordinates ----------- post /plateau/max-coordinates
   c. Option to create a plateau with user provided min and max coordinates e.g (-5, -5) (5,5) ---------post /plateau/min-max-coordinates/?minX=6&minY=6&maxX=20&maxY=20


2. Input required for Rover creation xPos, yPos, Direction(N or S or E or W) , InstructionSet (should comtain any of the following letters L R M
  End point Post /rovers?x=5&y=6&dir=S&instructionSet=LRM

3 Post /rovers/execute
Will execute all the rovers instuction sequentially

4. Get /rovers
Will return all the rovers that were added

On execution of rover instruction, the x , y and dir of rover is updated
Refactor this to store the initial x, y and dir as well. Also to clean up the code, create a class Position that stores the x, y and dir

The above endpoints were created to handle input like the ones mentioned in the example above

Run the application:
mvn clean package
mvn spring-boot:run

Once the application is running, you can access it using a web browser or Postman. I used Postman for testing
To verify the application is up:
http://localhost:8080/hello
You should see the response: "Hello, Mars!"

Create Default Plateau:
POST http://localhost:8080/plateau/default

Create Plateau with Max Coordinates:
http://localhost:8080/plateau/max-coordinates?maxX=10&maxY=10

Add Rover:
http://localhost:8080/rovers?x=1&y=2&dir=N&instructionSet=LMLMLMLMM

Execute Instructions for All Rovers:
http://localhost:8080/rovers/execute

Get Rovers:
http://localhost:8080/rovers

