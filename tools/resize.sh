#!/bin/sh
pic='core/assets/Mandala-Coloring-Pages-17-1024x1013.jpg'
output='core/assets/thumb.png'
thumb_size='256x256'
gm convert "$pic" -resize "$thumb_size^" -gravity center -crop "$thumb_size"+0+0 -repage "$thumb_size" -colors 256 "$output"
