# Bitscope-java-API

During the development of the API I have found that the VM register set provided by bitscope is not the complete register set. 
I am currently in the process of reverse engineering existing solutions like PiLab and the bitscope DSO. 

My findings will be put in this list in order to better serve the community.

Bitscope registers

| register        | register address  | Register name  | Register description  |
|-----	|----	|------------------------------------	|-----------------------------------------------------	|
| R0  	| 0  	| Data Register                      	| Assemble input data here.                           	|
| R1  	| 1  	| Address Register                   	| Register address pointer.                           	|
| R2  	| 2  	| Source Address Register            	| Source register address pointer.                    	|
| R3  	| 3  	| Sample Pre-load (Low Byte)         	| Spock Counter/RAM address (low byte).               	|
| R4  	| 4  	| Sample Pre-load (High Byte)        	| Spock Counter/RAM address (high byte).              	|
| R5  	| 5  	| Trigger Logic Byte                 	| Logic values to match for trigger.                  	|
| R6  	| 6  	| Trigger Mask Byte                  	| Don't care logic values for trigger.                	|
| R7  	| 7  	| Spock Option Byte                  	| Trigger and PG1 setup in Spock.                     	|
| R9  	| 9  	| Counter Capture (Low Byte)         	| Spock Counter/RAM address capture (low byte).       	|
| R10 	| 16 	| Counter Capture (High Byte)        	| Spock Counter/RAM address capture (high byte).      	|
| R14 	| 20 	| Input/Attenuation                  	| Alt/Chop channel input/attenuation settings.        	|
| R15 	| 21 	| Dump Size                          	| Number of samples dumped per request.               	|
| R8  	| 8  	| Trace Register                     	| Trace mode selection.                               	|
| R11 	| 17 	| Post Trigger Delay (Low Byte)      	| Delay after trigger (low byte).                     	|
| R12 	| 18 	| Post Trigger Delay (High Byte)     	| Delay after trigger (high byte).                    	|
| R13 	| 19 	| Time-base Expansion                	| Time-base expansion factor.                         	|
| R20 	| 32 	| Pre-Trigger Delay                  	| Programmed to pre-fill buffer before trigger match. 	|
| R16 	| 22 	| EEPROM Data                        	| EEPROM Data Register.                               	|
| R17 	| 23 	| EEPROM Address                     	| EEPROM Address Register.                            	|
| R18 	| 24 	| POD Transmit                       	| Byte sent to POD.                                   	|
| R19 	| 25 	| POD Receive                        	| Byte received from POD.                             	|
| R21 	| 33 	| Frequency Timer Pre-Load           	| Number of signal cycles to count.                   	|
| R22 	| 34 	| Frequency Pre-scale                	| Input frequency pre-scale divider.                  	|
| R23 	| 35 	| Period Pulse Count                 	| Used for period measurements.                       	|
| R24 	| 36 	| Mode Select                        	| Specifies dump mode (0..3).                         	|
| R25 	| 37 	| Mark Register                      	| Process this many bytes.                            	|
| R26 	| 38 	| Skip Register                      	| Skip this many bytes.                               	|
| R27 	| 39 	| Repeat Count                       	| Repeat this number of times.                        	|
| R28 	| 40 	| Mode Select                        	| Specifies scan mode (0..3).                         	|
| R29 	| 41 	| Scan range (Low Byte)              	| Number of samples to scan (low byte).               	|
| R30 	| 48 	| Scan range (High Byte)             	| Number of samples to scan (high byte).              	|
| R31 	| 49 	| Positive Transition Level          	| Level to compare agains't (00..FF).                 	|
| R32 	| 50 	| Negative Transition Level          	| Level to compare agains't (00..FF).                 	|
| R33 	| 51 	| Mode Select                        	| Specifies delayed trace mode (0..3).                	|
| R34 	| 52 	| Delay Counter (Byte 0)             	| Delay duration timer, 2 uS units, (byte 0).         	|
| R35 	| 53 	| Delay Counter (Byte 1)             	| as above (byte 1).                                  	|
| R36 	| 54 	| Delay Counter (Byte 2)             	| as above (byte 2).                                  	|
| R37 	| 55 	| Delay Counter (Byte 3)             	| as above (byte 3).                                  	|
| R38 	| 56 	| Capture Counter (Byte 0)           	| Capture duration timer, 2 uS units, (byte 0).       	|
| R39 	| 57 	| Capture Counter (Byte 1)           	| as above (byte 1).                                  	|
| R40 	| 64 	| Capture Counter (Byte 2)           	| as above (byte 2).                                  	|
| R41 	| 65 	| Capture Counter (Byte 3)           	| as above (byte 3).                                  	|
| R42 	| 66 	| Slow Clock Sample Counter (Byte 0) 	| Sample counter (byte 0).                            	|
| R43 	| 67 	| Slow Clock Sample Counter (Byte 1) 	| as above (byte 1).                                  	|
| R44 	| 68 	| Pre-Trigger Filter                 	| Filter control pre-trigger                          	|
| R45 	| 69 	| Post-Trigger Filter                	| Filter control post-trigger.                        	|
| R46 	| 70 	| Mode Select                        	| Specifies logic trace mode (0,1,4).                 	|
| R47 	| 71 	| Logic Sample Rate                  	| Specifies the logic sample rate.                    	|
| R61 	| 97 	| POD Start Cell Width               	| Specifies POD I/O Start Cell Width.                 	|
| R62 	| 98 	| POD Bit Cell Width                 	| Specifies POD I/O Bit Cell Width.                   	|
| R63 	| 99 	| Baud Rate Divisor                  	| Specifies the USART Baud Rate.                      	|
| Rxx 	| xx 	| extra                  	| extra                      	|
