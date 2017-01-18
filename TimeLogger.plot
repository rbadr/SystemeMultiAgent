set terminal svg
set output 'target/watorSimulationTime.svg'
set   autoscale
set xtic auto                          
set ytic auto                          
set title "Wator Simulation"
set xlabel "Time (Thicks)"
set ylabel "Population"
set autoscale x
set autoscale y
set key top left

plot  "target/simulationTime.csv" using 1:2 title 'Fish' with line, \
      "" using 1:3 title 'Sharks' with line