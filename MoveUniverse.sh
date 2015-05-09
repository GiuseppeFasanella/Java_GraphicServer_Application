#! /bin/bash

java RunGraphicServer&
#This delay is meant to give the time in order to set the connection
sleep 3 
java Move
#This delay is meant to give you the time to see your universe, before closing the canvas
sleep 4
#This find the process ID of RunGraphicServer  (which is in background) and kills the process
#awk selects the first column of the output
kill $(ps ax  | grep RunGraphicServer |grep Sl | awk '{print $1}')

#####Alternative:
#Open another shell and type "java Move"
#gnome-terminal -e "bash -c \"java Move; exec bash\""