set terminal svg
set output 'target/watorSimulation.svg'
set   autoscale
set xtic auto                          
set ytic auto                          
set title "Wator Simulation"
set xlabel "Fish"
set ylabel "Sharks"
set autoscale x
set autoscale y
set key off

h1 = 117/360.0
h2 = 227/360.0

set palette defined ( 0 1 1 1, 1 0 0 0 )

plot  "target/simulationTime.csv" using 2:3:1 w line lc palette
