# Bitscope-java-API

<h1> Currently in alpha </h1>

Currently the API is in alpha. This means there are quite a lot of bugs, missing functionalities and of cource incomplete code.

Check the Main.java in order to see the example code.
The output of the scope is an array of doubles representing the voltages.

Working:
- get voltage graph array
- select channel
- Set voltage range
- Set timebase => still a bit unreliable in setting diffirent timebases
- Select trigger channel
- get sample frequency
- Chop channels => still needs a bit of love

ToDo:
- Set trigger_level => currently at zero-crossing

Future functions
- Function generator
- Logic analyzer
- IO control? unconfirmed functionality

Special thanks to the creators of PiLab. Thanks to their program I was able to reverse engineer the undocumented register functions and commands in the bitscope micro.