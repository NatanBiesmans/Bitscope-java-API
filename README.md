# Bitscope-java-API

<b> This API isn't functional at the moment!!!!!!!!!!!</b>

I will use this readme as a kind of blog to give my findings and help others where possible. 

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

After a preliminary examination of the PiLab code I have made this table that explains the deviations in the registers compared to the table on the bitscope website. Blank indexes can be seen as undefined or the same as the table provided by bitscope. (like I said, preliminary)

| register | hex addres | Measuring registers                                   | Analog wave form generator                        |
|----------|------------|-------------------------------------------------------|---------------------------------------------------|
| 0        | 0          |                                                       |                                                   |
| 1        | 1          |                                                       |                                                   |
| 2        | 2          |                                                       |                                                   |
| 3        | 3          |                                                       |                                                   |
| 4        | 4          |                                                       |                                                   |
| 5        | 5          | trigger value                                         |                                                   |
| 6        | 6          | trigger mask                                          |                                                   |
| 7        | 7          |                                                       |                                                   |
| 8        | 8          | set spock address                                     |                                                   |
| 9        | 9          | set spock address                                     |                                                   |
| 10       | A          | set spock address                                     |                                                   |
| 11       | B          | set spock address                                     |                                                   |
| 12       | C          |                                                       |                                                   |
| 13       | D          |                                                       |                                                   |
| 14       | E          |                                                       |                                                   |
| 15       | F          |                                                       |                                                   |
| 16       | 10         |                                                       |                                                   |
| 17       | 11         |                                                       |                                                   |
| 18       | 12         |                                                       |                                                   |
| 19       | 13         |                                                       |                                                   |
| 20       | 14         | clock scale                                           |                                                   |
| 21       | 15         | clock scale                                           |                                                   |
| 22       | 16         |                                                       |                                                   |
| 23       | 17         |                                                       |                                                   |
| 24       | 18         |                                                       |                                                   |
| 25       | 19         |                                                       |                                                   |
| 26       | 1A         |                                                       |                                                   |
| 27       | 1B         |                                                       |                                                   |
| 28       | 1C         | dump size                                             |                                                   |
| 29       | 1D         | dump size                                             |                                                   |
| 30       | 1E         | dump mode                                             |                                                   |
| 31       | 1F         |                                                       |                                                   |
| 32       | 20         |                                                       |                                                   |
| 33       | 21         | trace mode                                            |                                                   |
| 34       | 22         |                                                       |                                                   |
| 35       | 23         |                                                       |                                                   |
| 36       | 24         |                                                       |                                                   |
| 37       | 25         |                                                       |                                                   |
| 38       | 26         | pre trigger cap                                       |                                                   |
| 39       | 27         | pre trigger cap                                       |                                                   |
| 40       | 28         | pre trigger delay                                     |                                                   |
| 41       | 29         | pre trigger delay                                     |                                                   |
| 42       | 2A         | post trigger cap                                      |                                                   |
| 43       | 2B         | post trigger cap                                      |                                                   |
| 44       | 2C         | timeout                                               |                                                   |
| 45       | 2D         | timeout                                               |                                                   |
| 46       | 2E         | set clock ticks, adjust timebase                      |                                                   |
| 47       | 2F         | set clock ticks, adjust timebase                      |                                                   |
| 48       | 30         | dump channel                                          |                                                   |
| 49       | 31         | buffer mode                                           |                                                   |
| 50       | 32         | edge trigger intro                                    |                                                   |
| 51       | 33         | edge trigger intro                                    |                                                   |
| 52       | 34         | edge trigger outro filter                             |                                                   |
| 53       | 35         | edge trigger outro filter                             |                                                   |
| 54       | 36         |                                                       |                                                   |
| 55       | 37         | analogue channel enable (1: channel A, 2: channel B ) |                                                   |
| 56       | 38         |                                                       |                                                   |
| 57       | 39         |                                                       |                                                   |
| 58       | 3A         |                                                       |                                                   |
| 59       | 3B         |                                                       |                                                   |
| 60       | 3C         |                                                       |                                                   |
| 61       | 3D         |                                                       |                                                   |
| 62       | 3E         |                                                       |                                                   |
| 63       | 3F         |                                                       |                                                   |
| 64       | 40         | stop watch trigger                                    |                                                   |
| 65       | 41         | stop watch trigger                                    |                                                   |
| 66       | 42         | stop watch trigger                                    |                                                   |
| 67       | 43         | stop watch trigger                                    |                                                   |
| 68       | 44         | dig comparitor trigger                                |                                                   |
| 69       | 45         | dig comparitor trigger                                |                                                   |
| 70       | 46         |                                                       | vp command mode                                   |
| 71       | 47         |                                                       | vp operating mode                                 |
| 72       | 48         |                                                       | vp option (control flag)                          |
| 73       | 49         |                                                       | vp option (control flag)                          |
| 74       | 4A         |                                                       | vp operating size                                 |
| 75       | 4B         |                                                       | vp operating size                                 |
| 76       | 4C         |                                                       | vp index, table start                             |
| 77       | 4D         |                                                       | vp index, table start                             |
| 78       | 4E         |                                                       | vp size, operation size (# samples)               |
| 79       | 4F         |                                                       | vp size, operation size (# samples)               |
| 80       | 50         |                                                       | vp clock (sample clock ticks) => ticks per period |
| 81       | 51         |                                                       | vp clock (sample clock ticks) => ticks per period |
| 82       | 52         |                                                       | vp modulo (table modulo size)                     |
| 83       | 53         |                                                       | vp modulo (table modulo size)                     |
| 84       | 54         |                                                       | vp level (output level)                           |
| 85       | 55         |                                                       | vp level (output level)                           |
| 86       | 56         |                                                       | vp offset(output offset)                          |
| 87       | 57         |                                                       | vp offset(output offset)                          |
| 88       | 58         |                                                       |                                                   |
| 89       | 59         |                                                       |                                                   |
| 90       | 5A         |                                                       | vp ratio (phase ratio)                            |
| 91       | 5B         |                                                       | vp ratio (phase ratio)                            |
| 92       | 5C         |                                                       | vp ratio (phase ratio)                            |
| 93       | 5D         |                                                       | vp ratio (phase ratio)                            |
| 94       | 5E         |                                                       | vp mark (mark count/ phase)                       |
| 95       | 5F         |                                                       | vp space(space count/phase)                       |
| 96       | 60         |                                                       | vp DacOutput (DAC level)                          |
| 97       | 61         |                                                       |                                                   |
| 98       | 62         |                                                       |                                                   |
| 99       | 63         |                                                       |                                                   |
| 100      | 64         | Voltage range low (Byte 0)                            |                                                   |
| 101      | 65         | Voltage range low (Byte 1)                            |                                                   |
| 102      | 66         | Voltage range high (Byte 0)                           |                                                   |
| 103      | 67         | Voltage range high (Byte 1)                           |                                                   |
| 104      | 68         | trigger level                                         |                                                   |
| 105      | 69         | trigger level                                         |                                                   |
| 106      | 6A         |                                                       |                                                   |
| 107      | 6B         |                                                       |                                                   |
| 108      | 6C         |                                                       |                                                   |
| 109      | 6D         |                                                       |                                                   |
| 110      | 6E         |                                                       |                                                   |
| 111      | 6F         |                                                       |                                                   |
| 112      | 70         |                                                       |                                                   |
| 113      | 71         |                                                       |                                                   |
| 114      | 72         |                                                       |                                                   |
| 115      | 73         |                                                       |                                                   |
| 116      | 74         |                                                       | sets logic pins direction: 0:input 1:output       |
| 117      | 75         |                                                       |                                                   |
| 118      | 76         |                                                       |                                                   |
| 119      | 77         |                                                       |                                                   |
| 120      | 78         |                                                       |                                                   |
| 121      | 79         |                                                       |                                                   |
| 122      | 7A         |                                                       |                                                   |
| 123      | 7B         |                                                       |                                                   |
| 124      | 7C         |                                                       | kitchen sink b, it enables the awg                |
| 125      | 7D         |                                                       |                                                   |
| 126      | 7E         |                                                       |                                                   |
| 127      | 7F         |                                                       |                                                   |
| 128      | 80         |                                                       |                                                   |
| 129      | 81         |                                                       |                                                   |
| 130      | 82         |                                                       | vp rise(rising edge at tick)                      |
| 131      | 83         |                                                       | vp rise(rising edge at tick)                      |
| 132      | 84         |                                                       | vp Fall(falling edge at tick)                     |
| 133      | 85         |                                                       | vp Fall(falling edge at tick)                     |
| 134      | 86         |                                                       | control the clock generator                       |
| 135      | 87         |                                                       |                                                   |
| 136      | 88         |                                                       |                                                   |
| 137      | 89         |                                                       |                                                   |
| 138      | 8A         |                                                       |                                                   |
| 139      | 8B         |                                                       |                                                   |
| 140      | 8C         |                                                       |                                                   |
| 141      | 8D         |                                                       |                                                   |
| 142      | 8E         |                                                       |                                                   |
| 143      | 8F         |                                                       |                                                   |
| 144      | 90         |                                                       |                                                   |
| 145      | 91         |                                                       |                                                   |
| 146      | 92         |                                                       |                                                   |
| 147      | 93         |                                                       |                                                   |
| 148      | 94         |                                                       |                                                   |
| 149      | 95         |                                                       |                                                   |
| 150      | 96         |                                                       |                                                   |
| 151      | 97         |                                                       |                                                   |
| 152      | 98         |                                                       |                                                   |
| 153      | 99         |                                                       | vp map (can enable output on l5)                  |
| 154      | 9A         |                                                       |                                                   |
| 155      | 9B         |                                                       |                                                   |
| 156      | 9C         |                                                       |                                                   |
| 157      | 9D         |                                                       |                                                   |
| 158      | 9E         |                                                       |                                                   |
| 159      | 9F         |                                                       |                                                   |
| 160      | A0         |                                                       |                                                   |
| 161      | A1         |                                                       |                                                   |
| 162      | A2         |                                                       |                                                   |
| 163      | A3         |                                                       |                                                   |
| 164      | A4         |                                                       |                                                   |
| 165      | A5         |                                                       |                                                   |
| 166      | A6         |                                                       |                                                   |
| 167      | A7         |                                                       |                                                   |
| 168      | A8         |                                                       |                                                   |
| 169      | A9         |                                                       |                                                   |
| 170      | AA         |                                                       |                                                   |
| 171      | AB         |                                                       |                                                   |
| 172      | AC         |                                                       |                                                   |
| 173      | AD         |                                                       |                                                   |
| 174      | AE         |                                                       |                                                   |
| 175      | AF         |                                                       |                                                   |
| 176      | B0         |                                                       |                                                   |
| 177      | B1         |                                                       |                                                   |
| 178      | B2         |                                                       |                                                   |
| 179      | B3         |                                                       |                                                   |
| 180      | B4         |                                                       |                                                   |
| 181      | B5         |                                                       |                                                   |
| 182      | B6         |                                                       |                                                   |
| 183      | B7         |                                                       |                                                   |
| 184      | B8         |                                                       |                                                   |
| 185      | B9         |                                                       |                                                   |
| 186      | BA         |                                                       |                                                   |
| 187      | BB         |                                                       |                                                   |
| 188      | BC         |                                                       |                                                   |
| 189      | BD         |                                                       |                                                   |
| 190      | BE         |                                                       |                                                   |
| 191      | BF         |                                                       |                                                   |
| 192      | C0         |                                                       |                                                   |
| 193      | C1         |                                                       |                                                   |
| 194      | C2         |                                                       |                                                   |
| 195      | C3         |                                                       |                                                   |
| 196      | C4         |                                                       |                                                   |
| 197      | C5         |                                                       |                                                   |
| 198      | C6         |                                                       |                                                   |
| 199      | C7         |                                                       |                                                   |
| 200      | C8         |                                                       |                                                   |
| 201      | C9         |                                                       |                                                   |
| 202      | CA         |                                                       |                                                   |
| 203      | CB         |                                                       |                                                   |
| 204      | CC         |                                                       |                                                   |
| 205      | CD         |                                                       |                                                   |
| 206      | CE         |                                                       |                                                   |
| 207      | CF         |                                                       |                                                   |
| 208      | D0         |                                                       |                                                   |
| 209      | D1         |                                                       |                                                   |
| 210      | D2         |                                                       |                                                   |
| 211      | D3         |                                                       |                                                   |
| 212      | D4         |                                                       |                                                   |
| 213      | D5         |                                                       |                                                   |
| 214      | D6         |                                                       |                                                   |
| 215      | D7         |                                                       |                                                   |
| 216      | D8         |                                                       |                                                   |
| 217      | D9         |                                                       |                                                   |
| 218      | DA         |                                                       |                                                   |
| 219      | DB         |                                                       |                                                   |
| 220      | DC         |                                                       |                                                   |
| 221      | DD         |                                                       |                                                   |
| 222      | DE         |                                                       |                                                   |
| 223      | DF         |                                                       |                                                   |
| 224      | E0         |                                                       |                                                   |
| 225      | E1         |                                                       |                                                   |
| 226      | E2         |                                                       |                                                   |
| 227      | E3         |                                                       |                                                   |
| 228      | E4         |                                                       |                                                   |
| 229      | E5         |                                                       |                                                   |
| 230      | E6         |                                                       |                                                   |
| 231      | E7         |                                                       |                                                   |
| 232      | E8         |                                                       |                                                   |
| 233      | E9         |                                                       |                                                   |
| 234      | EA         |                                                       |                                                   |
| 235      | EB         |                                                       |                                                   |
| 236      | EC         |                                                       |                                                   |
| 237      | ED         |                                                       |                                                   |
| 238      | EE         |                                                       |                                                   |
| 239      | EF         |                                                       |                                                   |
| 240      | F0         |                                                       |                                                   |
| 241      | F1         |                                                       |                                                   |
| 242      | F2         |                                                       |                                                   |
| 243      | F3         |                                                       |                                                   |
| 244      | F4         |                                                       |                                                   |
| 245      | F5         |                                                       |                                                   |
| 246      | F6         |                                                       |                                                   |
| 247      | F7         |                                                       |                                                   |
| 248      | F8         |                                                       |                                                   |
| 249      | F9         |                                                       |                                                   |
| 250      | FA         |                                                       |                                                   |
| 251      | FB         |                                                       |                                                   |
| 252      | FC         |                                                       |                                                   |
| 253      | FD         |                                                       |                                                   |
| 254      | FE         |                                                       |                                                   |
| 255      | FF         |                                                       |                                                   |
