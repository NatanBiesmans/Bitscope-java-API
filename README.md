# Bitscope-java-API

<h1> Currently in alpha </h1>

Currently the API is in alpha. This means there are quite a lot of bugs, missing functionalities and of cource incomplete code.

Check the Main.java in order to see the example code.
The output of the scope is an array of doubles representing the voltages.

Working:
- print hex values of trace
- select channel
- Set voltage range
- Set timebase

ToDo:
- Set trigger_level
- Chop channels

Future functions
- Function generator
- Logic analyzer
- IO control? unconfirmed functionality

Special thanks to the creators of PiLab. Thanks to their program I was able to reverse engineer the undocumented register functions and commands in the bitscope micro.