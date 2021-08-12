# rubiks-cube
Fully functional Rubiks Cube in Java.<br>
<br>
Each Cube is composed of 27 individial Cubits which determine the state of the Cube.<br>
Each Cubit stores a byte[] of data, which represents the orientation of itself, only one orientation is the solved orientation - {0, 1, 2, 3, 4, 5}.<br>
The Cube is solved once each Cubit maintains this orientation.<br>
<br>
For the Cube to rotate each side, like a Rubiks Cube, a relative position for each Cubit must be stored - therefore, the Cube stores a Cubit[] of data, similar to how each Cubit stores a byte[] of data.<br>
To rotate a face, the Cubits inside the Cubit[] for that face are shifted around, and each Cubit's orientation is rotated appropriately.<br>
By using individual Cubits, we solve the issue of the adjascent faces also needing to be rotated, as these Cubits do so automatically.<br>
