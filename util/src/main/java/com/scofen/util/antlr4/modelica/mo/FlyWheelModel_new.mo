within Modelica_Network.Examples.HOC;
model FlyWheelModel_new "飞轮测试模型"

  annotation (Diagram(coordinateSystem(extent = {{-140.0, -100.0}, {140.0, 100.0}}, 
    preserveAspectRatio = false, 
    grid = {2.0, 2.0})), 
    Icon(coordinateSystem(extent = {{-100.0, -100.0}, {100.0, 100.0}}, 
      preserveAspectRatio = false, 
      grid = {2.0, 2.0})), 
    experiment(StartTime = 0, StopTime = 40, Interval = 0.1, Algorithm = "Rkfix4", Tolerance = 0.0001, IntegratorStep = 0.1, DoublePrecision = true, StoreEventValue = true));
  Modelica.Blocks.Sources.TimeTable timeTable(table = {{0.0, 0.0}, {20, 1000}, {40, 0}, {60, 1000}, {80, 0}, {10000, 0}})
    annotation (Placement(transformation(origin = {-78.0, 48.0}, 
      extent = {{-10.0, -10.0}, {10.0, 10.0}})));
  Modelica.Blocks.Interfaces.RealOutput speed_rad_s "单位：rad/s" annotation (Placement(transformation(origin = {74.0, 36.0}, 
    extent = {{-10.0, -10.0}, {10.0, 10.0}})));
  Modelica.Blocks.Interfaces.RealOutput speed_rpm "单位：rpm" annotation (Placement(transformation(origin = {54.0, -24.0}, 
    extent = {{-10.0, -10.0}, {10.0, 10.0}})));
  Modelica.Blocks.Math.Gain gain(k = 0.10471975511966)
    annotation (Placement(transformation(origin = {38.0, 36.0}, 
      extent = {{-10.0, -10.0}, {10.0, 10.0}})));
  Modelica.Blocks.Sources.Trapezoid trapezoid(amplitude = 1000, rising = 20, falling = 20, period = 60, width = 10)
    annotation (Placement(transformation(origin = {-110.0, 0.0}, 
      extent = {{-10.0, -10.0}, {10.0, 10.0}})));
  Modelica.Blocks.Continuous.CriticalDamping criticalDamping
    annotation (Placement(transformation(origin = {-70.0, 0.0}, 
      extent = {{-10.0, -10.0}, {10.0, 10.0}})));



  inner Blocks.UDPComp.StartUDPComp ucobj
    annotation (Placement(transformation(origin = {-21.00000000000003, 44.0}, 
      extent = {{-17.0, -16.0}, {17.0, 16.0}})));
  Blocks.HOC.RWDataSender rWDataSender(ip_to = "192.168.0.126", port_to = 12001)
    annotation (Placement(transformation(origin = {124.0, -23.0}, 
      extent = {{-28.0, -29.0}, {28.0, 29.0}})));
equation 
  connect(criticalDamping.y, speed_rpm)
    annotation (Line(origin = {12.0, -7.0}, 
      points = {{-71.0, 7.0}, {6.0, 7.0}, {6.0, -17.0}, {42.0, -17.0}}, 
      color = {0, 0, 127}));
  connect(criticalDamping.y, gain.u)
    annotation (Line(origin = {6.0, 23.0}, 
      points = {{-65.0, -23.0}, {12.0, -23.0}, {12.0, 13.0}, {20.0, 13.0}}, 
      color = {0, 0, 127}));
  connect(gain.y, speed_rad_s)
    annotation (Line(origin = {52.0, 36.0}, 
      points = {{-3.0, 0.0}, {22.0, 0.0}}, 
      color = {0, 0, 127}));
  connect(trapezoid.y, criticalDamping.u)
    annotation (Line(origin = {-90.0, 0.0}, 
      points = {{-9.0, 0.0}, {8.0, 0.0}}, 
      color = {0, 0, 127}));
  connect(speed_rpm, rWDataSender.u)
    annotation (Line(origin = {73.0, -24.0}, 
      points = {{-19.0, 0.0}, {17.0, 0.0}, {17.0, 1.0}}, 
      color = {0, 0, 127}));
end FlyWheelModel_new;