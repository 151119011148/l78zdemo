within Modelica_Network;
package YJNY "应急能源"

  package Battery "蓄电池"
    annotation (Protection(access = Access.diagram));
    model CapacitorWithSOC "带SOC的电容"
      parameter Modelica.SIunits.Capacitance C0 = 1 "SOC=0时电容值" 
        annotation (Dialog(tab = "电容参数", group = "电容参数"));
      parameter Real KCSOC = 1 "电容值与SOC的线性系数" 
        annotation (Dialog(tab = "电容参数", group = "电容参数"));
      parameter Modelica.SIunits.Temp_K Tref = 293.15 "参考温度" 
        annotation (Dialog(tab = "电容参数", group = "电容参数"));
      Modelica.Blocks.Math.Gain gain(k = KCSOC)
        annotation (Placement(transformation(extent = {{-60, 40}, {-40, 60}})));
      Modelica.Blocks.Math.Add add
        annotation (Placement(transformation(extent = {{-20, 20}, {0, 40}})));
      Modelica.Blocks.Sources.Constant const(k = C0)
        annotation (Placement(transformation(extent = {{-60, -60}, {-40, -40}})));
      Modelica.Blocks.Interfaces.RealInput u1 "输入信号连接器" 
        annotation (Placement(transformation(origin = {0, 100}, rotation = 270, extent = {{-20, -20}, {20, 20}})));
      VariableCapacitor variableCapacitor(useHeatPort = true) "带热接口的可变电容" annotation (Placement(transformation(
        extent = {{-10, -10}, {10, 10}}, 
        rotation = 90, 
        origin = {40, 0})));

      Modelica.Electrical.Analog.Interfaces.NegativePin n1 "负极" 
        annotation (Placement(transformation(extent = {{30, 90}, {50, 110}}), 
          iconTransformation(extent = {{90, -10}, {110, 10}})));
      Modelica.Electrical.Analog.Interfaces.PositivePin p1 "正极(potential p.v > n.v for positive voltage drop v)" 
        annotation (Placement(transformation(extent = {{30, -109}, {50, -89}}), 
          iconTransformation(extent = {{-110, -10}, {-90, 10}})));
      extends Modelica.Electrical.Analog.Interfaces.ConditionalHeatPort(T = Tref, useHeatPort = true);
    equation 
      connect(gain.y, add.u1)
        annotation (Line(origin = {-30.5, 43.0}, 
          points = {{-9.0, 7.0}, {1.0, 7.0}, {1.0, -7.0}, {9.0, -7.0}}, 
          color = {0, 0, 127}));
      connect(const.y, variableCapacitor.C)
        annotation (Line(origin = {-5.0, -25.0}, 
          points = {{-34.0, -25.0}, {-19.0, -25.0}, {-19.0, 25.0}, {34.0, 25.0}}, 
          color = {0, 0, 127}));
      connect(gain.u, u1)
        annotation (Line(origin = {-40.0, 75.0}, 
          points = {{-22.0, -25.0}, {-32.0, -25.0}, {-32.0, 33.0}, {40.0, 33.0}, {40.0, 25.0}}, 
          color = {0, 0, 127}));
      connect(variableCapacitor.n, n1)
        annotation (Line(origin = {40.0, 55.0}, 
          points = {{0.0, -45.0}, {0.0, 45.0}}, 
          color = {0, 0, 255}));

      connect(variableCapacitor.p, p1)
        annotation (Line(origin = {40.0, -54.5}, 
          points = {{0.0, 45.0}, {0.0, -45.0}}, 
          color = {0, 0, 255}));

      connect(heatPort, variableCapacitor.heatPort)
        annotation (Line(origin = {40.0, -50.0}, 
          points = {{-40.0, -50.0}, {-40.0, 0.0}, {22.0, 0.0}, {22.0, 50.0}, {10.0, 50.0}}, 
          color = {191, 0, 0}));
      annotation (Diagram(coordinateSystem(
        preserveAspectRatio = true, 
        extent = {{-100, -100}, {100, 100}}, 
        grid = {1, 1}), graphics), 
        Icon(coordinateSystem(preserveAspectRatio = true, 
          extent = {{-100, -100}, {100, 100}}, 
          grid = {1, 1}), 
          graphics = {
          Line(points = {{-40, 0}, {40, 0}}, color = {0, 0, 255}, smooth = Smooth.None, thickness = 0.5, rotation = 90, 
          origin = {-20, 0}), 
          Line(points = {{-40, 0}, {40, 0}}, color = {0, 0, 255}, smooth = Smooth.None, thickness = 0.5, rotation = 90, 
          origin = {20, 0}), 
          Line(points = {{20, 0}, {110, 0}}, color = {0, 0, 127}, smooth = Smooth.None), 
          Line(points = {{0, 40}, {0, 80}}, color = {0, 0, 255}, smooth = Smooth.None), 
          Line(points = {{0.5, 45}, {0.5, -45}}, color = {0, 0, 255}, smooth = Smooth.None, rotation = 90, origin = {-65, -0.5}), 
          Text(extent = {{-20, 93}, {100, 73}}, color = {95, 95, 95}, fillPattern = FillPattern.Solid, textString = "SOC")}), 
        Protection(access = Access.diagram));
      connect(const.y, add.u2)
        annotation (Line(origin = {-30.5, -13.0}, 
          points = {{-9.0, -37.0}, {1.0, -37.0}, {1.0, 37.0}, {9.0, 37.0}}, 
          color = {0, 0, 127}));
    end CapacitorWithSOC;
    model CycleNr "循环"
      parameter Real CycleNini = 1 "初始循环";
      //   annotation (Dialog(tab = "循环次数参数", group = "循环次数电池参数"));
      parameter Modelica.SIunits.ElectricCharge Q0 = 40 "蓄电池容量";
      //   annotation (Dialog(tab = "循环次数参数", group = "循环次数电池参数"));
      Modelica.Blocks.Math.Abs abs1 "取正" 
        annotation (Placement(transformation(extent = {{-36, -26}, {-16, -6}})));
      Modelica.Blocks.Interfaces.RealInput u1 "输入信号连接器" 
        annotation (Placement(transformation(extent = {{-136, -36}, {-96, 4}}), 
          iconTransformation(extent = {{-120, -20}, {-80, 20}})));
      Modelica.Blocks.Interfaces.RealOutput y "输出" 
        annotation (Placement(transformation(extent = {{90, -10}, {110, 10}}), 
          iconTransformation(extent = {{100, -10}, {120, 10}})));
      Modelica.Blocks.Continuous.Integrator Q "取整" 
        annotation (Placement(transformation(extent = {{22, -26}, {42, -6}})));
    equation 
      // 计算循环次数 //
      y = floor(CycleNini + Q.y / 2 / Q0 / 3600);
      connect(u1, abs1.u) annotation (Line(
        points = {{-116, -16}, {-38, -16}}, 
        color = {0, 0, 127}, 
        smooth = Smooth.None));
      connect(abs1.y, Q.u) annotation (Line(
        points = {{-15, -16}, {20, -16}}, 
        color = {0, 0, 127}, 
        smooth = Smooth.None));
      annotation (Diagram(graphics), Icon(graphics = {
        Line(
        points = {{-60, -56}, {-40, -56}, {-40, -36}, {-20, -36}, {-20, -16}, {0, -16}, {0, 
        4}, {20, 4}, {20, 24}, {40, 24}, {40, 44}, {60, 44}, {60, 44}}, 
        color = {95, 95, 95}, 
        smooth = Smooth.None), 
        Rectangle(
        extent = {{-100, 100}, {100, -100}}, 
        lineColor = {0, 0, 127}), 
        Text(
        extent = {{-58, 86}, {54, 48}}, 
        lineColor = {95, 95, 95}, 
        fillPattern = FillPattern.Solid, 
        textString = "Cycles"), 
        Line(points = {{-68, -72}, {-68, 76}}, 
        color = {192, 192, 192}), 
        Polygon(
        points = {{-68, 82}, {-76, 60}, {-60, 60}, {-68, 82}}, 
        lineColor = {192, 192, 192}, 
        fillColor = {192, 192, 192}, 
        fillPattern = FillPattern.Solid), 
        Line(points = {{-80, -64.3976}, {78, -64.3976}}, color = {192, 192, 192}), 
        Polygon(
        points = {{82, -64.3976}, {60, -56.3976}, {60, -72.3976}, {82, -64.3976}}, 
        lineColor = {192, 192, 192}, 
        fillColor = {192, 192, 192}, 
        fillPattern = FillPattern.Solid)}), 
        Protection(access = Access.diagram));
    end CycleNr;
    model ResistorWithSOC "带SOC的可变电阻"
      parameter Modelica.SIunits.Resistance R0 = 1 "SOC=0时电阻值" 
        annotation (Dialog(tab = "电阻参数", group = "电阻参数"));
      parameter Real Rsoc = 1 "电阻值与SOC的相关线性系数" 
        annotation (Dialog(tab = "电阻参数", group = "电阻参数"));
      parameter Modelica.SIunits.Temp_K Tref = 293.15 "默认温度" 
        annotation (Dialog(tab = "电阻参数", group = "电阻参数"));
      Modelica.Electrical.Analog.Basic.VariableResistor variableResistor(
        T_ref = Tref, 
        alpha = 5e-5, 
        useHeatPort = true)
        annotation (Placement(transformation(origin = {0, 0}, rotation = 0, extent = {{-10, -10}, {10, 10}})));
      Modelica.Electrical.Analog.Interfaces.PositivePin p1 "正极(正极电压高于负极电压造成的电压降为V)" 
        annotation (Placement(transformation(extent = {{-110, -10}, {-90, 10}}), 
          iconTransformation(extent = {{-110, -10}, {-90, 10}})));
      Modelica.Electrical.Analog.Interfaces.NegativePin n1 "负极" 
        annotation (Placement(transformation(extent = {{85, -10}, {105, 10}}), 
          iconTransformation(extent = {{90, -10}, {110, 10}})));
      Modelica.Blocks.Math.Gain gain(
        k = Rsoc)
        annotation (Placement(transformation(origin = {-70, 55}, rotation = 270, extent = {{-10, -10}, {10, 10}})));
      Modelica.Blocks.Interfaces.RealInput u1 "信号输入" 
        annotation (Placement(transformation(origin = {0, 100}, rotation = -90, extent = {{-20, -20}, {20, 20}})));
      extends Modelica.Electrical.Analog.Interfaces.ConditionalHeatPort(
        T = Tref, useHeatPort = true);
      Modelica.Blocks.Math.Add add
        annotation (Placement(transformation(extent = {{-41, 20}, {-21, 40}})));
      Modelica.Blocks.Sources.Constant const(
        k = R0)
        annotation (Placement(transformation(extent = {{-95, 5}, {-75, 25}})));
    equation 
      connect(variableResistor.p, p1)
        annotation (Line(origin = {-55.0, 0.0}, 
          points = {{45.0, 0.0}, {-45.0, 0.0}}, 
          color = {0, 0, 255}));
      connect(gain.u, u1)
        annotation (Line(origin = {-35.0, 83.5}, 
          points = {{-35.0, -17.0}, {-35.0, 22.0}, {35.0, 22.0}, {35.0, 17.0}}, 
          color = {0, 0, 127}));
      connect(n1, n1)
        annotation (Line(origin = {95.0, 0.0}, 
          points = {{0.0, 0.0}, {0.0, 0.0}}, 
          color = {0, 0, 255}, 
          thickness = 1.0));
      connect(variableResistor.heatPort, heatPort)
        annotation (Line(origin = {0.0, -55.0}, 
          points = {{0.0, 45.0}, {0.0, -45.0}}, 
          color = {191, 0, 0}));
      connect(const.y, variableResistor.R)
        annotation (Line(origin = {-37.0, 13.0}, 
          points = {{-37.0, 2.0}, {-23.0, 2.0}, {-23.0, 3.0}, {37.0, 3.0}, {37.0, -1.0}}, 
          color = {0, 0, 127}));
      connect(gain.y, add.u1)
        annotation (Line(origin = {-56.5, 40.0}, 
          points = {{-14.0, 4.0}, {-14.0, -4.0}, {14.0, -4.0}}, 
          color = {0, 0, 127}));
      connect(variableResistor.n, n1)
        annotation (Line(origin = {52.5, 0.0}, 
          points = {{-43.0, 0.0}, {43.0, 0.0}}));annotation (Diagram(coordinateSystem(
            preserveAspectRatio = true, 
            extent = {{-100, -100}, {100, 100}}, 
            grid = {1, 1}), graphics), 
          Icon(coordinateSystem(preserveAspectRatio = true, 
            extent = {{-100, -100}, {100, 100}}, 
            grid = {1, 1}), 
            graphics = {
            Rectangle(extent = {{-20, 60}, {20, -60}}, color = {0, 0, 255}, rotation = 90, origin = {0, 0}), 
            Line(points = {{-30, -2.027e-6}, {28, 0}, {28, 0}}, color = {0, 0, 127}, smooth = Smooth.None, rotation = 90, origin = {0, 50}), 
            Line(points = {{0, -15.5}, {0, 15.5}}, color = {0, 0, 255}, smooth = Smooth.None, rotation = 90, origin = {75.49, -0.01392}), 
            Line(points = {{0, 20}, {0, -20}}, color = {0, 0, 255}, smooth = Smooth.None, rotation = 90, origin = {-80, 0}), 
            Text(extent = {{-21, 97}, {99, 77}}, color = {95, 95, 95}, fillPattern = FillPattern.Solid, textString = "SOC")}), 
          Protection(access = Access.diagram));
      connect(const.y, add.u2)
        annotation (Line(origin = {-58.5, 19.5}, 
          points = {{-16.0, -5.0}, {-2.0, -5.0}, {-2.0, 5.0}, {16.0, 5.0}}, 
          color = {0, 0, 127}));
    end ResistorWithSOC;
    model SOC "荷电状态"
      parameter Real Ns = 1 "太阳能电池数目" 
        annotation (Dialog(group = "蓄电池组参数"));
      parameter Real Np = 1 "并联的太阳能电池数目" 
        annotation (Dialog(group = "蓄电池组参数"));
      parameter Real SOCini = 0.5 "初始荷电状态";
      // annotation (Dialog(tab = "电荷状态计算参数", group = "电荷状态计算参数"));
      parameter Real Q0 = 2.727 "蓄电池容量(单位 Ah)";
      //annotation (Dialog(tab = "电荷状态计算参数", group = "电荷状态计算参数"));
      parameter Modelica.SIunits.Charge Max = Q0 * 3600 "充电最大容量";
      // annotation (Dialog(tab = "电荷状态计算参数", group = "电荷状态计算参数"));
      parameter Real Min = 0 "放电的最小容量";
      // annotation (Dialog(tab = "电荷状态计算参数", group = "电荷状态计算参数"));
      Modelica.Blocks.Continuous.LimIntegrator limIntegrator(k = 1, 
        outMax = Max, 
        outMin = Min, 
        y_start = SOCini * Q0 * 3600) "输出限制" 
        annotation (Placement(transformation(extent = {{-38, -24}, {-18, -4}})));
      Modelica.Blocks.Math.Gain gain(k = 1 / 3600 / Q0) "换算" 
        annotation (Placement(transformation(extent = {{20, -10}, {40, 10}})));
      Modelica.Blocks.Interfaces.RealInput u "信号输入" 
        annotation (Placement(transformation(extent = {{-136, -34}, {-96, 6}}), 
          iconTransformation(extent = {{-120, -18}, {-82, 20}})));
      Modelica.Blocks.Interfaces.RealOutput SOC "信号输出" 
        annotation (Placement(transformation(extent = {{100, -10}, {120, 10}}), 
          iconTransformation(extent = {{100, -10}, {120, 10}})));
    equation 
      connect(limIntegrator.y, gain.u)
        annotation (Line(
          points = {{-17, -14}, {0, -14}, {0, 0}, {18, 0}}, 
          color = {0, 0, 127}, 
          smooth = Smooth.None));
      connect(u, limIntegrator.u)
        annotation (Line(
          points = {{-116, -14}, {-40, -14}}, 
          color = {0, 0, 127}, 
          thickness = 0.5, 
          smooth = Smooth.None));
      connect(gain.y, SOC)
        annotation (Line(
          points = {{41, 0}, {110, 0}}, 
          color = {0, 0, 127}, 
          thickness = 0.5, 
          smooth = Smooth.None));
      annotation (Diagram(graphics), 
        Icon(graphics = {Rectangle(
          extent = {{-100, 100}, {100, -100}}, 
          lineColor = {0, 0, 127}), 
          Text(
          extent = {{-72, 40}, {80, -32}}, 
          lineColor = {95, 95, 95}, 
          lineThickness = 0.5, 
          textString = "SOC"), 
          Line(points = {{-72, -84}, {-72, 64}}, 
          color = {192, 192, 192}), 
          Polygon(
          points = {{-72, 80}, {-80, 58}, {-64, 58}, {-72, 80}}, 
          lineColor = {192, 192, 192}, 
          fillColor = {192, 192, 192}, 
          fillPattern = FillPattern.Solid), 
          Line(points = {{-80, -78.3976}, {78, -78.3976}}, color = {192, 192, 192}), 
          Polygon(
          points = {{100, -78.3976}, {78, -70.3976}, {78, -86.3976}, {100, -78.3976}}, 
          lineColor = {192, 192, 192}, 
          fillColor = {192, 192, 192}, 
          fillPattern = FillPattern.Solid), 
          Line(
          points = {{-70, -50}, {12, 50}, {84, 50}, {84, 50}}, 
          color = {95, 95, 95}, 
          smooth = Smooth.None)}), 
        Protection(access = Access.diagram));
    end SOC;
    model VariableCapacitor "理想的可变电容"
      extends Modelica.Electrical.Analog.Interfaces.OnePort;
      parameter Modelica.SIunits.Temperature T_ref = 293.15 "参考温度";
      parameter Modelica.SIunits.LinearTemperatureCoefficient alpha = 5e-5 "与电容值相关的温度的线性系数";
      Modelica.Blocks.Interfaces.RealInput C
        annotation (Placement(transformation(
          origin = {0, 110}, 
          extent = {{-20, -20}, {20, 20}}, 
          rotation = 270)));
      parameter Modelica.SIunits.Capacitance Cmin = Modelica.Constants.eps "电容的取值范围";
      Modelica.SIunits.ElectricCharge Q;
      extends Modelica.Electrical.Analog.Interfaces.ConditionalHeatPort(
        T = T_ref, useHeatPort = true);
      // protected 
      Modelica.SIunits.Capacitance C_actual;
    equation 
      assert(C >= 0, "Capacitance C (= " + 
        String(C) + ") has to be >= 0!");
      // protect solver from index change
      C_actual = C * (1 + alpha * (T_heatPort - T_ref));
      Q = noEvent(max(C_actual, Cmin)) * v;
      i = der(Q);
      LossPower = v * i;
      annotation (Documentation(info = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">
<HTML><HEAD>
<META content=\"text/html; charset=gb2312\" http-equiv=Content-Type>
<META name=GENERATOR content=\"MSHTML 11.00.9600.18212\"></HEAD>
<BODY>
<P>线性的电容对应电流与电压存在如下关系：&nbsp;<I><B>i = dQ/dt</B></I> with <I><B>Q = C * 
v</B></I><B> </B>. </P>
<P>电容值C作为输入信号给出。&nbsp;而C要求不为负值。 
因此通过Constant设定一个Cmin，使得C不会处于0到Cmin之间。</P></BODY></HTML>
 ", 
        revisions = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">
<HTML><HEAD>
<META content=\"text/html; charset=gb2312\" http-equiv=Content-Type>
<META name=GENERATOR content=\"MSHTML 10.00.9200.17148\"></HEAD>
<BODY>
<UL>
  <LI><I>June 7, 2004 </I>by Christoph Clauss<BR>changed, docu added
  <LI><I>April 30, 2004</I> by Anton Haumer<BR>implemented. 
</LI></UL></BODY></HTML>
 "), 
        Icon(coordinateSystem(preserveAspectRatio = true, extent = {{-100, -100}, 
          {100, 100}}), graphics = {
          Line(points = {{-90, 0}, {-14, 0}}, color = {0, 0, 255}), 
          Line(points = {{14, 0}, {90, 0}}, color = {0, 0, 255}), 
          Line(points = {{0, 90}, {0, 30}}, color = {0, 0, 255}), 
          Line(
          points = {{-14, 28}, {-14, -28}}, 
          thickness = 0.5, 
          color = {0, 0, 255}), 
          Line(
          points = {{14, 28}, {14, -28}}, 
          thickness = 0.5, 
          color = {0, 0, 255}), 
          Text(
          extent = {{-144, -43}, {156, -83}}, 
          textString = "%name", 
          lineColor = {0, 0, 255})}), 
        Diagram(coordinateSystem(preserveAspectRatio = true, extent = {{-100, 
          -100}, {100, 100}}), graphics = {
          Line(points = {{-96, 0}, {-14, 0}}, color = {0, 0, 255}), 
          Line(points = {{14, 0}, {96, 0}}, color = {0, 0, 255}), 
          Line(points = {{0, 90}, {0, 30}}, color = {0, 0, 255}), 
          Line(
          points = {{-14, 28}, {-14, -28}}, 
          thickness = 0.5, 
          color = {0, 0, 255}), 
          Line(
          points = {{14, 28}, {14, -28}}, 
          thickness = 0.5, 
          color = {0, 0, 255})}), 
        Protection(access = Access.diagram));
    end VariableCapacitor;
    model VoltageTranslator "电压转换器"
      parameter Real table[:,:] = [0.0073, 
        2.94394; 0.0217786, 3.127; 0.0798548, 3.39703; 0.0961887, 3.43822; 
        0.166969, 3.51144; 0.225045, 3.54805; 0.294011, 3.58467; 0.364791, 3.6167; 
        0.437387, 3.65332; 0.508167, 3.69451; 0.577132, 3.73112; 0.6098, 3.74943; 
        0.664247, 3.79519; 0.724138, 3.85011; 0.793103, 3.91876; 0.863884, 
        4.00114; 0.945554, 4.10183; 1, 4.17963];
      parameter Real Ns = 1 "单位个数";
      Modelica.Blocks.Tables.CombiTable1Ds SOCVoltageTranslate(table = table) "SOC与电压的转换对照" 
        annotation (Placement(transformation(extent = {{-40, -10}, {-20, 10}})));
      Modelica.Blocks.Interfaces.RealInput SOC "信号输入" 
        annotation (Placement(transformation(extent = {{-120, -20}, {-80, 20}}), 
          iconTransformation(extent = {{-120, -20}, {-80, 20}})));
      Modelica.Blocks.Interfaces.RealOutput V "信号输出" 
        annotation (Placement(transformation(extent = {{100, -10}, {120, 10}}), 
          iconTransformation(extent = {{100, -10}, {120, 10}})));
      Modelica.Blocks.Math.Gain gain(k = Ns)
        annotation (Placement(transformation(extent = {{0, -10}, {20, 10}})));
    equation 
      connect(SOC, SOCVoltageTranslate.u)
        annotation (Line(points = {{-100, 0}, {-42, 0}}, color = {0, 0, 255}, smooth = Smooth.None));
      connect(SOCVoltageTranslate.y[1], gain.u)
        annotation (Line(points = {{-19, 0}, {-2, 0}}, color = {0, 0, 127}));
      connect(gain.y, V)
        annotation (Line(points = {{21, 0}, {110, 0}}, color = {0, 0, 127}));
      annotation (Diagram(graphics), 
        Icon(graphics = {
          Rectangle(
          extent = {{-100, 100}, {100, -100}}, 
          lineColor = {0, 0, 127}), 
          Text(
          extent = {{-82, 84}, {76, 26}}, 
          lineColor = {95, 95, 95}, 
          fillColor = {85, 170, 255}, 
          fillPattern = FillPattern.Solid, 
          textString = "SOC"), 
          Text(
          extent = {{-84, -26}, {74, -84}}, 
          lineColor = {95, 95, 95}, 
          fillColor = {85, 170, 255}, 
          fillPattern = FillPattern.Solid, 
          textString = "V"), 
          Line(
          points = {{-4, 30}, {-4, -20}, {-4, -18}}, 
          color = {95, 95, 95}, 
          smooth = Smooth.None), 
          Polygon(
          points = {{-4, -24}, {-12, -2}, {4, -2}, {-4, -24}}, 
          lineColor = {192, 192, 192}, 
          fillColor = {192, 192, 192}, 
          fillPattern = FillPattern.Solid)}), 
        Protection(access = Access.diagram));
    end VoltageTranslator;
    model Battery_U "蓄电池通用模型"
      //"蓄电池单体"

      parameter Boolean isFixed = true "电流初值固定";
      parameter Real Ns = 66 "电池数目" 
        annotation (Dialog(group = "蓄电池组参数"));
      parameter Real Np = 3 "并联数" 
        annotation (Dialog(group = "蓄电池组参数"));
      parameter Real N_units = 1 "单位数" 
        annotation (Dialog(group = "蓄电池组参数"));
      parameter Real Q0 = 60 "蓄电池容量(单位Ah)" 
        annotation (Dialog(tab = "蓄电池单元参数", group = "蓄电池参数"));
      parameter Real SOCini = 1 "初始荷电状态" 
        annotation (Dialog(tab = "蓄电池单元参数", group = "蓄电池参数"));
      parameter Real CycleNini = 1 "初始循环" 
        annotation (Dialog(tab = "蓄电池单元参数", group = "蓄电池参数"));
      parameter Real MaxCharge = Q0 * 3600 * Np * N_units "充电的最大容量" 
        annotation (Dialog(tab = "蓄电池单元参数", group = "蓄电池参数"));
      parameter Real MinDischarge = 0 "放电的最小容量" 
        annotation (Dialog(tab = "蓄电池单元参数", group = "蓄电池参数"));
      parameter Modelica.SIunits.Resistance Rs0 = 0.0125 "蓄电池内阻" 
        annotation (Dialog(tab = "蓄电池单元参数", group = "组件参数"));
      parameter Real KRsSOC = -0.000375 "阻值与SOC的线性相关系数" 
        annotation (Dialog(tab = "蓄电池单元参数", group = "组件参数"));
      parameter Modelica.SIunits.Resistance RD0 = 0.00082 "SOC=0时RC模块的电阻" 
        annotation (Dialog(tab = "蓄电池单元参数", group = "组件参数"));
      parameter Real KRDSOC = 0 "RC模块的电阻与SOC的线性相关系数" 
        annotation (Dialog(tab = "蓄电池单元参数", group = "组件参数"));
      parameter Modelica.SIunits.Capacitance CD0 = 9000 "SOC=0时RC模块的电容" 
        annotation (Dialog(tab = "蓄电池单元参数", group = "组件参数"));
      parameter Real KCDSOC = 4500 "RC模块的电容与SOC的线性相关系数" 
        annotation (Dialog(tab = "蓄电池单元参数", group = "组件参数"));
      parameter Modelica.SIunits.Resistance Rself = 8352 "自放电电阻" 
        annotation (Dialog(tab = "蓄电池单元参数", group = "组件参数"));
      parameter Modelica.SIunits.Current selfResistor_i_start = -0.001501
        annotation (Dialog(tab = "初始值参数", group = "初始值参数"));
      // extends Energy.Interfaces.SupplyPort;
      Modelica.Blocks.Interfaces.RealOutput SOC "荷电状态" 
        annotation (Placement(transformation(origin = {-90, 40}, rotation = 180, extent = {{-10, -10}, {10, 10}})));
      Modelica.Thermal.HeatTransfer.Interfaces.HeatPort_a HeatPort_a "温度输入接口" 
        annotation (Placement(transformation(extent = {{-10, -110}, {10, -90}})));
      ResistorWithSOC resistorWithSOC(R0 = 
        RD0 * Ns / Np / N_units, Rsoc = KRDSOC) annotation (Placement(transformation(extent = {{-10, -10}, {10, 10}}, rotation = 90, origin = {50, 30})));

      ResistorWithSOC Resistor_in(R0 = 
        Rs0 * Ns / Np / N_units, Rsoc = KRsSOC) annotation (Placement(transformation(extent = {{-10, -10}, {10, 10}}, rotation = 90, origin = {30, -10})));

      Modelica.Electrical.Analog.Sensors.CurrentSensor currentSensor
        annotation (Placement(transformation(
          extent = {{-10, -10}, {10, 10}}, 
          rotation = -90, 
          origin = {30, 70})));
      Modelica.Electrical.Analog.Sources.SignalVoltage signalVoltage
        annotation (Placement(transformation(
          extent = {{-11, 10}, {11, -10}}, 
          rotation = -90, 
          origin = {30, -50})));
      SOC sOC(Ns = Ns, 
        Np = Np, 
        SOCini = SOCini, 
        Q0 = 
        Q0 * Np * N_units, Max = MaxCharge, 
        Min = MinDischarge) annotation (Placement(transformation(extent = {{-4, 54}, {-24, 74}})));

      CycleNr cycleNr(CycleNini = CycleNini, 
        Q0 = 
        Q0 * Np * N_units) annotation (Placement(transformation(extent = {{-40, 76}, {-60, 96}})));
      VoltageTranslator voltageTranslator(Ns = Ns) "电压与SOC转换关系" annotation (Placement(transformation(extent = {{-40, -60}, {-20, -40}})));

      CapacitorWithSOC capacitorWithSOC(C0 = 
        CD0 * Np * N_units / Ns, KCSOC = KCDSOC) annotation (Placement(transformation(extent = {{-10, -10}, {10, 10}}, rotation = 90, origin = {10, 30})));

      Modelica.Electrical.Analog.Basic.Resistor selfResistor(i(start = 
        selfResistor_i_start, 
        fixed = isFixed), 
        R = Rself * Ns / Np)
        annotation (Placement(
          transformation(
            extent = {{-10, -10}, {10, 10}}, 
            rotation = 90, 
            origin = {80, -10})));
      Modelica.Blocks.Interfaces.RealOutput cycles
        annotation (Placement(transformation(extent = {{-80, -60}, {-100, -40}}), 
          iconTransformation(extent = {{-80, -50}, {-100, -30}})));
      Modelica.SIunits.Power Power_In "充电功率";
      Modelica.SIunits.Power Power_Out "放电功率";
      Modelica.SIunits.Voltage Voltage;
      //-------------------动态组件部分-----------------------//
      //protected 
      Real High = 180 * SOC;
      Real SOC_Warning = noEvent(min(max(0.5 * (4 * SOC - 2), 0), 1)) "蓄电池剩余电量警告线";
      Real R = noEvent(min(2 * (1 - SOC_Warning), 1) * 255);
      Real G = noEvent(min(2 * SOC_Warning, 1) * 255);
      Real B = 0;
      Modelica.Electrical.Analog.Interfaces.PositivePin p
        annotation (Placement(transformation(origin = {100.0, 50.0}, 
          extent = {{-10.0, -10.0}, {10.0, 10.0}})));
      Modelica.Electrical.Analog.Interfaces.NegativePin n
        annotation (Placement(transformation(origin = {100.0, -50.0}, 
          extent = {{-10.0, -10.0}, {10.0, 10.0}})));
    equation 
      //-------------------数值显示部分-----------------------//
      Power_In = if p.i > 0 then abs((p.v - n.v) * p.i) else 0;
      Power_Out = if p.i < 0 then abs((p.v - n.v) * p.i) else 0;
      Voltage = p.v;
      //------------------------------------------------------// 
      connect(resistorWithSOC.n1, currentSensor.n)
        annotation (Line(origin = {40.0, 50.0}, 
          points = {{10.0, -10.0}, {10.0, 2.0}, {-10.0, 2.0}, {-10.0, 10.0}}, 
          color = {0, 0, 255}));
      connect(resistorWithSOC.p1, Resistor_in.n1)
        annotation (Line(origin = {40.0, 10.0}, 
          points = {{10.0, 10.0}, {10.0, -4.0}, {-10.0, -4.0}, {-10.0, -10.0}}, 
          color = {0, 0, 255}));
      connect(Resistor_in.p1, signalVoltage.p)
        annotation (Line(origin = {30.0, -29.5}, 
          points = {{0.0, 10.0}, {0.0, -10.0}}, 
          color = {0, 0, 255}));
      connect(currentSensor.i, cycleNr.u1)
        annotation (Line(origin = {-10.0, 78.0}, 
          points = {{29.0, -8.0}, {15.0, -8.0}, {15.0, 8.0}, {-30.0, 8.0}}, 
          color = {255, 0, 0}, 
          thickness = 0.5));
      connect(capacitorWithSOC.p1, Resistor_in.n1)
        annotation (Line(origin = {20.0, 10.0}, 
          points = {{-10.0, 10.0}, {-10.0, -4.0}, {10.0, -4.0}, {10.0, -10.0}}, 
          color = {0, 0, 255}));
      connect(capacitorWithSOC.n1, currentSensor.n)
        annotation (Line(origin = {20.0, 50.0}, 
          points = {{-10.0, -10.0}, {-10.0, 2.0}, {10.0, 2.0}, {10.0, 10.0}}, 
          color = {0, 0, 255}));
      connect(cycleNr.y, cycles)
        annotation (Line(origin = {-75.5, 18.0}, 
          points = {{15.0, 68.0}, {7.0, 68.0}, {7.0, -68.0}, {-15.0, -68.0}}, 
          color = {255, 0, 0}, 
          thickness = 0.5));
      connect(signalVoltage.n, selfResistor.p)
        annotation (Line(origin = {55.0, -45.0}, 
          points = {{-25.0, -16.0}, {-25.0, -25.0}, {25.0, -25.0}, {25.0, 25.0}}, 
          color = {0, 0, 255}, 
          thickness = 0.5));
      connect(currentSensor.i, sOC.u)
        annotation (Line(origin = {8.05, 67.05}, 
          points = {{11.0, 3.0}, {-3.0, 3.0}, {-3.0, -3.0}, {-12.0, -3.0}}, 
          color = {0, 255, 128}, 
          thickness = 0.5));
      connect(sOC.SOC, SOC)
        annotation (Line(origin = {-57.5, 52.0}, 
          points = {{33.0, 12.0}, {-18.0, 12.0}, {-18.0, -12.0}, {-33.0, -12.0}}, 
          color = {0, 255, 128}, 
          thickness = 0.5));
      connect(sOC.SOC, voltageTranslator.SOC)
        annotation (Line(origin = {-42.5, 7.0}, 
          points = {{18.0, 57.0}, {-18.0, 57.0}, {-18.0, -57.0}, {3.0, -57.0}}, 
          color = {0, 255, 128}, 
          thickness = 0.5));
      connect(voltageTranslator.V, signalVoltage.v)
        annotation (Line(origin = {2.0, -50.0}, 
          points = {{-21.0, 0.0}, {16.0, 0.0}}, 
          color = {0, 0, 127}, 
          thickness = 0.5));

      connect(sOC.SOC, resistorWithSOC.u1)
        annotation (Line(origin = {0.0, 47.0}, 
          points = {{-25.0, 17.0}, {-40.0, 17.0}, {-40.0, -2.0}, {40.0, -2.0}, {40.0, -17.0}}, 
          color = {0, 255, 128}, 
          thickness = 0.5));
      connect(sOC.SOC, capacitorWithSOC.u1)
        annotation (Line(origin = {-20.0, 47.0}, 
          points = {{-5.0, 17.0}, {-20.0, 17.0}, {-20.0, -17.0}, {20.0, -17.0}}, 
          color = {0, 255, 128}, 
          thickness = 0.5));
      connect(sOC.SOC, Resistor_in.u1)
        annotation (Line(origin = {-10.0, 27.0}, 
          points = {{-15.0, 37.0}, {-30.0, 37.0}, {-30.0, -37.0}, {30.0, -37.0}}, 
          color = {0, 255, 128}, 
          thickness = 0.5));
      connect(selfResistor.p, n)
        annotation (Line(origin = {90.0, -35.0}, 
          points = {{-10.0, 15.0}, {-10.0, -15.0}, {10.0, -15.0}}, 
          thickness = 0.5));
      connect(currentSensor.p, selfResistor.n)
        annotation (Line(origin = {55.0, 43.0}, 
          points = {{-25.0, 37.0}, {-25.0, 43.0}, {25.0, 43.0}, {25.0, -43.0}}, 
          thickness = 0.5));
      connect(p, selfResistor.n)
        annotation (Line(origin = {90.0, 25.0}, 
          points = {{10.0, 25.0}, {-10.0, 25.0}, {-10.0, -25.0}}, 
          thickness = 0.5));
      connect(resistorWithSOC.heatPort, HeatPort_a)
        annotation (Line(origin = {35.0, -35.0}, 
          points = {{25.0, 65.0}, {35.0, 65.0}, {35.0, -65.0}, {-35.0, -65.0}}, 
          color = {191, 0, 0}, 
          thickness = 0.5));
      connect(Resistor_in.heatPort, HeatPort_a)
        annotation (Line(origin = {30.0, -55.0}, 
          points = {{10.0, 45.0}, {30.0, 45.0}, {30.0, -45.0}, {-30.0, -45.0}}, 
          color = {191, 0, 0}, 
          thickness = 0.5));
      connect(selfResistor.heatPort, HeatPort_a)
        annotation (Line(origin = {47.5, -55.0}, 
          points = {{43.0, 45.0}, {48.0, 45.0}, {48.0, -45.0}, {-48.0, -45.0}}, 
          color = {191, 0, 0}, 
          thickness = 0.5));
      connect(capacitorWithSOC.heatPort, HeatPort_a)
        annotation (Line(origin = {30.0, -35.0}, 
          points = {{-10.0, 65.0}, {-3.0, 65.0}, {-3.0, 45.0}, {30.0, 45.0}, {30.0, -65.0}, {-30.0, -65.0}}, 
          color = {191, 0, 0}, 
          thickness = 0.5));
      annotation (Diagram(graphics), 
        Icon(graphics = {
          Polygon(points = {{60, 80}, {80, 100}, {80, -80}, {60, -100}, {60, 80}}, color = {0, 0, 255}, smooth = Smooth.None, fillColor = {255, 85, 255}, fillPattern = FillPattern.Solid), 
          Polygon(points = {{-80, 80}, {-60, 100}, {80, 100}, {60, 80}, {-80, 80}}, color = {0, 0, 255}, smooth = Smooth.None, fillColor = {213, 170, 255}, fillPattern = FillPattern.Solid), 
          Rectangle(extent = {{68, 72}, {84, 52}}, color = {0, 0, 255}, fillPattern = FillPattern.Solid, fillColor = {255, 170, 255}), 
          Polygon(points = {{68, 72}, {74, 78}, {90, 78}, {84, 72}, {84, 72}, 
          {68, 72}}, color = {0, 0, 255}, smooth = Smooth.None, fillColor = {255, 170, 255}, fillPattern = FillPattern.Solid), 
          Polygon(points = {{90, 78}, {90, 58}, {84, 52}, {84, 72}, {90, 78}}, color = {0, 0, 255}, smooth = Smooth.None, fillColor = {255, 0, 0}, fillPattern = FillPattern.Solid), 
          Rectangle(extent = {{68, -58}, {84, -78}}, color = {0, 0, 255}, fillPattern = FillPattern.Solid, fillColor = {255, 170, 255}), 
          Polygon(points = {{68, -58}, {74, -52}, {90, -52}, {84, -58}, {84, -58}, 
          {68, -58}}, color = {0, 0, 255}, smooth = Smooth.None, fillPattern = FillPattern.Solid, fillColor = {255, 170, 255}), 
          Polygon(points = {{90, -52}, {90, -72}, {84, -78}, {84, -58}, {90, -52}}, color = {0, 0, 255}, smooth = Smooth.None, fillColor = {0, 0, 255}, fillPattern = FillPattern.Solid), 
          Line(points = {{88, 65}, {100, 65}, {100, 50}}, color = {255, 0, 0}, thickness = 1, smooth = Smooth.None), 
          Line(points = {{88, -65}, {100, -65}, {100, -50}}, color = {0, 0, 0}, thickness = 1, smooth = Smooth.None), 
          Text(extent = {{154.4, 120.1}, {155.3, 81.2}}, textString = "充电功率
%Power_In", color = {255, 0, 0}), 
          Text(extent = {{147, -81.9}, {147.9, -120.8}}, textString = "放电功率
%Power_Out"), 
          Line(points = {{0, 17}, {0, -17}}, thickness = 0.5, color = {255, 0, 0}, rotation = 270, origin = {146.5, 75.8}), 
          Polygon(points = {{-5.955, 4}, {5.955, 4}, {0.045, -4}}, fillColor = {255, 0, 0}, fillPattern = FillPattern.Solid, color = {255, 0, 0}, rotation = 270, 
          origin = {125.5, 76}), 
          Line(points = {{0, 17}, {0, -17}}, thickness = 0.5, rotation = 90, origin = {146.5, -75}), 
          Polygon(points = {{-5.955, 4}, {5.955, 4}, {0.045, -4}}, fillColor = {0, 0, 255}, fillPattern = FillPattern.Solid, rotation = 90, origin = {159.5, -74.97}), 
          Rectangle(extent = {{-80, 0}, {60, 180}}, color = {0, 0, 255}, fillColor = {0, 255, 0}, fillPattern = FillPattern.Solid, dynamicFillColorR = R, 
          dynamicFillColorG = G, dynamicFillColorB = B, dynamicHeight = High, origin = {0, -100}), 
          Rectangle(extent = {{-80, 80}, {60, -100}}), 
          Text(extent = {{-46, 8}, {32, -23}}, color = {75, 0, 130}, fillColor = {0, 255, 255}, fillPattern = FillPattern.Solid, textString = "%SOC")}), 
        Documentation(info = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">
<HTML xmlns:v = \"urn:schemas-microsoft-com:vml\" xmlns:o = 
\"urn:schemas-microsoft-com:office:office\"><HEAD>
<META content=\"text/html; charset=gb2312\" http-equiv=Content-Type>
<META name=GENERATOR content=\"MSHTML 10.00.9200.17148\"></HEAD>
<BODY><SPAN 
style=\"FONT-SIZE: 12pt; FONT-FAMILY: 宋体; LINE-HEIGHT: 125%; mso-ascii-font-family: 'Times New Roman'; mso-hansi-font-family: 'Times New Roman'; mso-bidi-font-family: 'Times New Roman'; mso-font-kerning: 1.0pt; mso-ansi-language: EN-US; mso-fareast-language: ZH-CN; mso-bidi-language: AR-SA\">
<P><SPAN 
style=\"FONT-SIZE: 12pt; FONT-FAMILY: 宋体; LINE-HEIGHT: 125%; mso-ascii-font-family: 'Times New Roman'; mso-hansi-font-family: 'Times New Roman'; mso-bidi-font-family: 'Times New Roman'; mso-font-kerning: 1.0pt; mso-ansi-language: EN-US; mso-fareast-language: ZH-CN; mso-bidi-language: AR-SA\"><STRONG>简介：</STRONG></SPAN></P>
<P><SPAN 
style=\"FONT-SIZE: 12pt; FONT-FAMILY: 宋体; LINE-HEIGHT: 125%; mso-ascii-font-family: 'Times New Roman'; mso-hansi-font-family: 'Times New Roman'; mso-bidi-font-family: 'Times New Roman'; mso-font-kerning: 1.0pt; mso-ansi-language: EN-US; mso-fareast-language: ZH-CN; mso-bidi-language: AR-SA\"><SPAN 
style=\"FONT-SIZE: 12pt; FONT-FAMILY: 宋体; LINE-HEIGHT: 125%; mso-ascii-font-family: 'Times New Roman'; mso-hansi-font-family: 'Times New Roman'; mso-bidi-font-family: 'Times New Roman'; mso-font-kerning: 1.0pt; mso-ansi-language: EN-US; mso-fareast-language: ZH-CN; mso-bidi-language: AR-SA\">&nbsp;&nbsp;&nbsp; 
蓄电池模型，在外部电能富余的情况下能够充电存储电能，并在外部电能不足时放电以维持电网电压稳定。</SPAN></SPAN></P>
<P><SPAN 
style=\"FONT-SIZE: 12pt; FONT-FAMILY: 宋体; LINE-HEIGHT: 125%; mso-ascii-font-family: 'Times New Roman'; mso-hansi-font-family: 'Times New Roman'; mso-bidi-font-family: 'Times New Roman'; mso-font-kerning: 1.0pt; mso-ansi-language: EN-US; mso-fareast-language: ZH-CN; mso-bidi-language: AR-SA\">&nbsp;&nbsp;&nbsp; 
</SPAN><SPAN 
style=\"FONT-SIZE: 12pt; FONT-FAMILY: 宋体; LINE-HEIGHT: 125%; mso-ascii-font-family: 'Times New Roman'; mso-hansi-font-family: 'Times New Roman'; mso-bidi-font-family: 'Times New Roman'; mso-font-kerning: 1.0pt; mso-ansi-language: EN-US; mso-fareast-language: ZH-CN; mso-bidi-language: AR-SA\">现阶段模型考虑电池容量和温度对蓄电池性能的影响，其中温度暂时考虑为恒定温度。蓄电池组模型通过参数设置来实现单片电池的串并联关系。在实际使用时，蓄电池与充放电调节器直接相连。</SPAN></P></SPAN>
<P><SPAN 
style=\"FONT-SIZE: 12pt; FONT-FAMILY: 宋体; LINE-HEIGHT: 125%; mso-ascii-font-family: 'Times New Roman'; mso-hansi-font-family: 'Times New Roman'; mso-bidi-font-family: 'Times New Roman'; mso-font-kerning: 1.0pt; mso-ansi-language: EN-US; mso-fareast-language: ZH-CN; mso-bidi-language: AR-SA\"><STRONG>模型原理:</STRONG></SPAN></P>
<P><SPAN 
style=\"FONT-SIZE: 12pt; FONT-FAMILY: 宋体; LINE-HEIGHT: 125%; mso-ascii-font-family: 'Times New Roman'; mso-hansi-font-family: 'Times New Roman'; mso-bidi-font-family: 'Times New Roman'; mso-font-kerning: 1.0pt; mso-ansi-language: EN-US; mso-fareast-language: ZH-CN; mso-bidi-language: AR-SA\"><SPAN 
lang=EN-US 
style=\"FONT-SIZE: 12pt; FONT-FAMILY: 'Times New Roman','serif'; LINE-HEIGHT: 125%; mso-font-kerning: 1.0pt; mso-ansi-language: EN-US; mso-fareast-language: ZH-CN; mso-bidi-language: AR-SA; mso-fareast-font-family: 宋体\"><v:shapetype 
id=_x0000_t75 stroked=\"f\" filled=\"f\" path=\"m@4@5l@4@11@9@11@9@5xe\" 
o:preferrelative=\"t\" o:spt=\"75\" coordsize=\"21600,21600\">&nbsp;</P>
<P align=center><IMG border=0 hspace=0 alt=\"\" align=baseline 
src=\".\\Pictures\\Energy\\锂离子蓄电池电路模型2.bmp\"></v:shapetype></SPAN></SPAN></P><SPAN 
style\"FONT-SIZE: 12pt; FONT-FAMILY: 宋体; LINE-HEIGHT: 125%; mso-ascii-font-family: 'Times New Roman'; mso-hansi-font-family: 'Times New Roman'; mso-bidi-font-family: 'Times New Roman'; mso-font-kerning: 1.0pt; mso-ansi-language: EN-US; mso-fareast-language: ZH-CN; mso-bidi-language: AR-SA\"><SPAN 
lang=EN-US 
style=\"FONT-SIZE: 12pt; FONT-FAMILY: 'Times New Roman','serif'; LINE-HEIGHT: 125%; mso-font-kerning: 1.0pt; mso-ansi-language: EN-US; mso-fareast-language: ZH-CN; mso-bidi-language: AR-SA; mso-fareast-font-family: 宋体\"><v:shapetype 
stroked=\"f\" filled=\"f\" path=\"m@4@5l@4@11@9@11@9@5xe\" o:preferrelative=\"t\" 
o:spt=\"75\" coordsize=\"21600,21600\">
<P class=MsoListParagraph style=\"MARGIN: 0cm 0cm 0pt; TEXT-INDENT: 24pt\"><SPAN 
style=\"FONT-FAMILY: 宋体; mso-ascii-font-family: 'Times New Roman'; mso-hansi-font-family: 'Times New Roman'\">模型主要基于锂离子蓄电池的二阶线性化电路模型建立。其中电阻</SPAN><SPAN 
lang=EN-US>R<SUB>1</SUB></SPAN><SPAN 
style=\"FONT-FAMILY: 宋体; mso-ascii-font-family: 'Times New Roman'; mso-hansi-font-family: 'Times New Roman'\">和</SPAN><SPAN 
lang=EN-US>C<SUB>1</SUB></SPAN><SPAN 
style=\"FONT-FAMILY: 宋体; mso-ascii-font-family: 'Times New Roman'; mso-hansi-font-family: 'Times New Roman'\">并联的组合可以反映电池的动态特性；电阻</SPAN><SPAN 
lang=EN-US>R<SUB>2</SUB></SPAN><SPAN 
style=\"FONT-FAMILY: 宋体; mso-ascii-font-family: 'Times New Roman'; mso-hansi-font-family: 'Times New Roman'\">可以反映电池的阻性；电动势</SPAN><SPAN 
lang=EN-US>E<SUB>hatt</SUB></SPAN><SPAN 
style=\"FONT-FAMILY: 宋体; mso-ascii-font-family: 'Times New Roman'; mso-hansi-font-family: 'Times New Roman'\">来反映电池的平缓的放电平台；输出端并联电阻</SPAN><SPAN 
lang=EN-US>R<SUB>3</SUB></SPAN><SPAN 
style=\"FONT-FAMILY: 宋体; mso-ascii-font-family: 'Times New Roman'; mso-hansi-font-family: 'Times New Roman'\">来反映电池自放电特性；温度对电池性能的影响，通过电阻和电容值与温度的关系来反映。</SPAN><SPAN 
lang=EN-US><o:p></o:p></SPAN></P>
<P class=MsoListParagraph style=\"MARGIN: 0cm 0cm 0pt; TEXT-INDENT: 24pt\"><SPAN 
style=\"FONT-FAMILY: 宋体; mso-ascii-font-family: 'Times New Roman'; mso-hansi-font-family: 'Times New Roman'\">其中，</SPAN><SPAN 
lang=EN-US><o:p></o:p></SPAN></P>
<P class=1 style=\"MARGIN: 0cm 0cm 0pt; mso-list: l0 level7 lfo1\"><SPAN 
lang=EN-US style=\"mso-fareast-font-family: 'Times New Roman'\"><SPAN 
style=\"mso-list: Ignore\">(1). </SPAN></SPAN><SPAN 
style=\"FONT-FAMILY: 宋体; mso-ascii-font-family: 'Times New Roman'; mso-hansi-font-family: 'Times New Roman'\">荷电状态（</SPAN><SPAN 
lang=EN-US>SOC</SPAN><SPAN 
style=\"FONT-FAMILY: 宋体; mso-ascii-font-family: 'Times New Roman'; mso-hansi-font-family: 'Times New Roman'\">）采用电流积分法计算，计算式如下</SPAN><SPAN 
lang=EN-US><o:p></o:p></SPAN></P>
<P class=MsoListParagraph style=\"MARGIN: 0cm 0cm 0pt; TEXT-INDENT: 24pt\"><SPAN 
lang=EN-US><SPAN 
style=\"POSITION: relative; TOP: 15pt; mso-text-raise: -15.0pt\"><v:shapetype 
id=_x0000_t75 stroked=\"f\" filled=\"f\" path=\"m@4@5l@4@11@9@11@9@5xe\" 
o:preferrelative=\"t\" o:spt=\"75\" coordsize=\"21600,21600\"><v:stroke 
joinstyle=\"miter\"></v:stroke><v:formulas><v:f 
eqn=\"if lineDrawn pixelLineWidth 0\"></v:f><v:f eqn=\"sum @0 1 0\"></v:f><v:f 
eqn=\"sum 0 0 @1\"></v:f><v:f eqn=\"prod @2 1 2\"></v:f><v:f 
eqn=\"prod @3 21600 pixelWidth\"></v:f><v:f 
eqn=\"prod @3 21600 pixelHeight\"></v:f><v:f eqn=\"sum @0 0 1\"></v:f><v:f 
eqn=\"prod @6 1 2\"></v:f><v:f eqn=\"prod @7 21600 pixelWidth\"></v:f><v:f 
eqn=\"sum @8 21600 0\"></v:f><v:f eqn=\"prod @7 21600 pixelHeight\"></v:f><v:f 
eqn=\"sum @10 21600 0\"></v:f></v:formulas><v:path o:connecttype=\"rect\" 
gradientshapeok=\"t\" o:extrusionok=\"f\"></v:path><o:lock aspectratio=\"t\" 
v:ext=\"edit\"></o:lock></v:shapetype></SPAN></SPAN><SPAN 
style=\"FONT-FAMILY: 宋体; mso-ascii-font-family: 'Times New Roman'; mso-hansi-font-family: 'Times New Roman'\"><IMG 
border=0 hspace=0 alt=\"\" align=baseline 
src=\".\\Pictures\\Energy\\0001.gif\">，</SPAN><SPAN lang=EN-US><o:p></o:p></SPAN></P>
<P class=MsoNormal style=\"MARGIN: 0cm 0cm 0pt\"><SPAN 
style=\"FONT-FAMILY: 宋体; mso-ascii-font-family: 'Times New Roman'; mso-hansi-font-family: 'Times New Roman'\">式中</SPAN><SPAN 
lang=EN-US></SPAN><SPAN 
style=\"FONT-FAMILY: 宋体; mso-ascii-font-family: 'Times New Roman'; mso-hansi-font-family: 'Times New Roman'\">为电池容量。电池充满电时，</SPAN><SPAN 
lang=EN-US>SOC=1</SPAN><SPAN 
style=\"FONT-FAMILY: 宋体; mso-ascii-font-family: 'Times New Roman'; mso-hansi-font-family: 'Times New Roman'\">。</SPAN><SPAN 
lang=EN-US><o:p></o:p></SPAN></P>
<P class=1 style=\"MARGIN: 0cm 0cm 0pt; mso-list: l0 level7 lfo1\"><SPAN 
lang=EN-US style=\"mso-fareast-font-family: 'Times New Roman'\"><SPAN 
style=\"mso-list: Ignore\">(2). </SPAN></SPAN><SPAN 
style=\"FONT-FAMILY: 宋体; mso-ascii-font-family: 'Times New Roman'; mso-hansi-font-family: 'Times New Roman'\">充放电循环次数（</SPAN><SPAN 
lang=EN-US>Cycle</SPAN><SPAN 
style=\"FONT-FAMILY: 宋体; mso-ascii-font-family: 'Times New Roman'; mso-hansi-font-family: 'Times New Roman'\">）根据电流积分来计算，计算式如下：</SPAN><SPAN 
lang=EN-US><o:p></o:p></SPAN></P>
<P class=MsoNormal 
style=\"MARGIN: 0cm 0cm 0pt 14.05pt; TEXT-INDENT: 9.95pt; mso-char-indent-count: .83\"><SPAN 
style=\"FONT-FAMILY: 宋体; mso-ascii-font-family: 'Times New Roman'; mso-hansi-font-family: 'Times New Roman'\"><IMG 
border=0 hspace=0 alt=\"\" align=baseline 
src=\".\\Pictures\\Energy\\0002.gif\">，</SPAN><SPAN lang=EN-US><o:p></o:p></SPAN></P>
<P class=MsoNormal style=\"MARGIN: 0cm 0cm 0pt\"><SPAN 
style=\"FONT-FAMILY: 宋体; mso-ascii-font-family: 'Times New Roman'; mso-hansi-font-family: 'Times New Roman'\">式中</SPAN><SPAN 
lang=EN-US>[]</SPAN><SPAN 
style=\"FONT-FAMILY: 宋体; mso-ascii-font-family: 'Times New Roman'; mso-hansi-font-family: 'Times New Roman'\">表示向下取整。</SPAN><SPAN 
lang=EN-US><o:p></o:p></SPAN></P>
<P><SPAN 
style=\"FONT-SIZE: 12pt; FONT-FAMILY: 宋体; LINE-HEIGHT: 125%; mso-ascii-font-family: 'Times New Roman'; mso-hansi-font-family: 'Times New Roman'; mso-bidi-font-family: 'Times New Roman'; mso-font-kerning: 1.0pt; mso-ansi-language: EN-US; mso-fareast-language: ZH-CN; mso-bidi-language: AR-SA\"><SPAN 
lang=EN-US style=\"mso-fareast-font-family: 'Times New Roman'\"><SPAN 
style=\"mso-list: Ignore\"><FONT face=\"Times New Roman\">(3). 
</FONT></SPAN></SPAN>荷电状态与输出电压关系使用插值表进行描述；</SPAN></P>
<P><SPAN 
style=\"FONT-SIZE: 12pt; FONT-FAMILY: 宋体; LINE-HEIGHT: 125%; mso-ascii-font-family: 'Times New Roman'; mso-hansi-font-family: 'Times New Roman'; mso-bidi-font-family: 'Times New Roman'; mso-font-kerning: 1.0pt; mso-ansi-language: EN-US; mso-fareast-language: ZH-CN; mso-bidi-language: AR-SA\"><STRONG>参考资料：</STRONG></SPAN></P><SPAN 
style=\"FONT-SIZE: 12pt; FONT-FAMILY: 宋体; LINE-HEIGHT: 125%; mso-ascii-font-family: 'Times New Roman'; mso-hansi-font-family: 'Times New Roman'; mso-bidi-font-family: 'Times New Roman'; mso-font-kerning: 1.0pt; mso-ansi-language: EN-US; mso-fareast-language: ZH-CN; mso-bidi-language: AR-SA\"><SPAN 
lang=EN-US 
style=\"TEXT-DECORATION: none; mso-fareast-font-family: 'Times New Roman'; text-underline: none\"><SPAN 
style=\"mso-list: Ignore\"><FONT face=\"Times New Roman\">
<P class=1 style=\"MARGIN: 0cm 0cm 0pt; mso-list: l0 level7 lfo1\"><SPAN 
lang=EN-US style=\"mso-fareast-font-family: 'Times New Roman'\"><SPAN 
style=\"mso-list: Ignore\">(1). </SPAN></SPAN><SPAN lang=EN-US 
style=\"FONT-SIZE: 10pt; LINE-HEIGHT: 125%\">M. Einhorn, F. V. Conte, C. Kral, J. 
Fleig, and R. Permann. Parametrization of an electrical battery model for 
dynamic system simulation in electric vehicles. Proceedings of the IEEE Vehicle 
Power and Propulsion Conference (VPPC), September 2010</SPAN><SPAN 
lang=EN-US><o:p></o:p></SPAN></P>
<P class=1 style=\"MARGIN: 0cm 0cm 0pt; mso-list: l0 level7 lfo1\"><SPAN 
lang=EN-US style=\"mso-fareast-font-family: 'Times New Roman'\"><SPAN 
style=\"mso-list: Ignore\">(2). </SPAN></SPAN><SPAN lang=EN-US 
style=\"FONT-SIZE: 10pt; LINE-HEIGHT: 125%\">M. Einhorn, F. V. Conte, C. Kral and 
etc. A Modelica Library for Simulation of Electric Energy Storages. Proceedings 
of the 8th International Modelica Conference, 2011.</SPAN><SPAN 
lang=EN-US><o:p></o:p></SPAN></P>
<P class=1 style=\"MARGIN: 0cm 0cm 0pt; mso-list: l0 level7 lfo1\"><SPAN 
lang=EN-US style=\"mso-fareast-font-family: 'Times New Roman'\"><SPAN 
style=\"mso-list: Ignore\">(3). </SPAN></SPAN><SPAN 
style=\"FONT-FAMILY: 宋体; mso-ascii-font-family: 'Times New Roman'; mso-hansi-font-family: 'Times New Roman'\">李国欣：《航天器电源技术概论》</SPAN> 
<SPAN 
style=\"FONT-FAMILY: 宋体; mso-ascii-font-family: 'Times New Roman'; mso-hansi-font-family: 'Times New Roman'\">中国宇航出版社</SPAN><SPAN 
lang=EN-US> P1222</SPAN><SPAN 
style=\"FONT-FAMILY: 宋体; mso-ascii-font-family: 'Times New Roman'; mso-hansi-font-family: 'Times New Roman'\">。</SPAN></FONT></SPAN></SPAN></SPAN></v:shapetype></SPAN></SPAN></P></BODY></HTML>
 "), 
        Protection(access = Access.diagram));
    end Battery_U;
    model Battery "蓄电池"

      Battery_U battery_U
        annotation (Placement(transformation(origin = {-9.999999999999996, 10.0}, 
          extent = {{-10.0, -10.0}, {10.0, 10.0}})));
      Modelica.Thermal.HeatTransfer.Sources.FixedTemperature fixedTemperature(T(displayUnit = "K") = 293.15)
        annotation (Placement(transformation(origin = {-30.0, -30.0}, 
          extent = {{-10.0, -10.0}, {10.0, 10.0}})));



      Modelica.Electrical.Analog.Interfaces.PositivePin p
        annotation (Placement(transformation(origin = {-100.0, 50.0}, 
          extent = {{-10.0, -10.0}, {10.0, 10.0}})));
      Modelica.Electrical.Analog.Interfaces.NegativePin n
        annotation (Placement(transformation(origin = {-100.0, -50.0}, 
          extent = {{-10.0, -10.0}, {10.0, 10.0}})));
      Modelica.Blocks.Interfaces.RealOutput Q0 "容量" 
        annotation (Placement(transformation(origin = {100.0, -40.0}, 
          extent = {{-10.0, -10.0}, {10.0, 10.0}})));
      annotation (Icon(coordinateSystem(extent = {{-100.0, -100.0}, {100.0, 100.0}}, 
        grid = {2.0, 2.0}), graphics = {Rectangle(origin = {-16.999999999999993, 2.842170943040401e-14}, 
        lineColor = {170, 170, 255}, 
        fillColor = {128, 128, 0}, 
        fillPattern = FillPattern.Solid, 
        extent = {{-75.0, 80.0}, {75.0, -80.0}}), Polygon(origin = {74.0, 9.999999999999995}, 
        fillColor = {128, 128, 0}, 
        fillPattern = FillPattern.Solid, 
        points = {{12.0, -72.0}, {16.0, -70.0}, {16.0, 90.0}, {-16.0, 70.0}, {-16.0, -90.0}}), Polygon(origin = {-1.0000000000000142, 90.0}, 
        fillColor = {128, 128, 0}, 
        fillPattern = FillPattern.Solid, 
        points = {{59.0, -10.0}, {91.0, 10.0}, {-59.0, 10.0}, {-91.0, -10.0}, {59.0, -10.0}})}), 
        Protection(access = Access.diagram));
      Modelica.Blocks.Interfaces.RealOutput Qs "剩余容量" 
        annotation (Placement(transformation(origin = {100.0, -20.0}, 
          extent = {{-10.0, -10.0}, {10.0, 10.0}})));
      Modelica.Blocks.Interfaces.RealOutput v "电压" 
        annotation (Placement(transformation(origin = {100.0, 50.0}, 
          extent = {{-10.0, -10.0}, {10.0, 10.0}})));
      Modelica.Blocks.Interfaces.RealOutput i "电流" 
        annotation (Placement(transformation(origin = {100.0, 29.999999999999993}, 
          extent = {{-10.0, -10.0}, {10.0, 10.0}})));
    equation 
      Q0 = battery_U.Q0;
      Qs = Q0 * battery_U.SOC;
      v = p.v - n.v;
      i = p.i;
      connect(fixedTemperature.port, battery_U.HeatPort_a)
        annotation (Line(origin = {-15.0, -15.0}, 
          points = {{-5.0, -15.0}, {5.0, -15.0}, {5.0, 15.0}}, 
          color = {191, 0, 0}));
      connect(battery_U.p, p)
        annotation (Line(origin = {-36.0, 33.0}, 
          points = {{36.0, -18.0}, {64.0, -18.0}, {64.0, 17.0}, {-64.0, 17.0}}, 
          color = {0, 0, 255}));
      connect(battery_U.n, n)
        annotation (Line(origin = {-37.0, -22.0}, 
          points = {{37.0, 27.0}, {63.0, 27.0}, {63.0, -28.0}, {-63.0, -28.0}}, 
          color = {0, 0, 255}));
    end Battery;
    package Machines "电机"


      model Machines_U "起发电机"
        extends Modelica.Electrical.Machines.Interfaces.PartialBasicDCMachine(
          final ViNominal = VaNominal - Modelica.Electrical.Machines.Thermal.convertResistance(
          Ra, 
          TaRef, 
          alpha20a, 
          TaNominal) * IaNominal - 
          Modelica.Electrical.Machines.Losses.DCMachines.brushVoltageDrop(brushParameters, 
          IaNominal), 
          final psi_eNominal = Lme * IeNominal, 
          redeclare final Modelica.Electrical.Machines.Thermal.DCMachines.ThermalAmbientDCPM thermalAmbient(
            final Tpm = TpmOperational), 
          redeclare final Modelica.Electrical.Machines.Interfaces.DCMachines.ThermalPortDCPM thermalPort, 

          redeclare final Modelica.Electrical.Machines.Interfaces.DCMachines.ThermalPortDCPM internalThermalPort, 

          redeclare final Modelica.Electrical.Machines.Interfaces.DCMachines.PowerBalanceDCPM powerBalance(
            final lossPowerPermanentMagnet = 0), 
          core(final w = airGapDC.w));
        final parameter Modelica.SIunits.Temperature TpmOperational = 293.15
          "Operational temperature of permanent magnet" 
          annotation (Dialog(group = "Operational temperatures"));
        Modelica.Electrical.Machines.BasicMachines.Components.AirGapDC airGapDC(
          final turnsRatio = turnsRatio, 
          final Le = Lme, 
          final quasiStationary = quasiStationary) annotation (Placement(
            transformation(
              extent = {{-10, -10}, {10, 10}}, 
              rotation = 270)));
        Modelica.Electrical.Analog.Basic.Ground eGround annotation (Placement(transformation(origin = {-20.0, -29.703132530120477}, 
          extent = {{-10.0, -10.0}, {10.0, 10.0}}, 
          rotation = 270.0)));


        Modelica.Electrical.Analog.Sources.ConstantCurrent ie(I = IeNominal)
          annotation (Placement(transformation(
            origin = {0, -40}, 
            extent = {{-10, -10}, {10, 10}})));
      protected 
        constant Modelica.SIunits.Inductance Lme = 1
          "Field excitation inductance";
        constant Modelica.SIunits.Current IeNominal = 1
          "Equivalent excitation current";
      equation 
        connect(eGround.p, ie.p) annotation (Line(origin = {-10.0, -35.0}, 
          points = {{0.0, 5.0}, {0.0, -5.0}}, 
          color = {0, 0, 255}));
        connect(airGapDC.pin_ep, ie.n)
          annotation (Line(origin = {10.0, -25.0}, 
            points = {{0.0, 15.0}, {0.0, -15.0}}, 
            color = {0, 0, 255}));
        connect(airGapDC.pin_en, eGround.p) annotation (Line(origin = {-10.0, -20.0}, 
          points = {{0.0, 10.0}, {0.0, -10.0}}, 
          color = {0, 0, 255}));
        connect(airGapDC.pin_ap, la.n) annotation (Line(origin = {10.0, 35.0}, 
          points = {{0.0, -25.0}, {0.0, 25.0}}, 
          color = {0, 0, 255}));
        connect(airGapDC.support, internalSupport) annotation (Line(origin = {10.0, -50.0}, 
          points = {{-20.0, 50.0}, {-65.0, 50.0}, {-65.0, -44.0}, {50.0, -44.0}, {50.0, -50.0}}));
        connect(airGapDC.flange, inertiaRotor.flange_a) annotation (Line(origin = {40.0, 0.0}, 
          points = {{-30.0, 0.0}, {30.0, 0.0}}));
        connect(airGapDC.pin_an, brush.p) annotation (Line(origin = {-10.0, 35.0}, 
          points = {{0.0, -25.0}, {0.0, 25.0}}, 
          color = {0, 0, 255}));annotation (
            defaultComponentName = "dcpm", 
            Icon(coordinateSystem(preserveAspectRatio = true, extent = {{-100, -100}, {
              100, 100}}), graphics = {Rectangle(
              extent = {{-130, 10}, {-100, -10}}, 
              fillColor = {0, 255, 0}, 
              fillPattern = FillPattern.Solid), Rectangle(
              extent = {{-100, 10}, {-70, -10}}, 
              fillColor = {255, 0, 0}, 
              fillPattern = FillPattern.Solid)}), 
            Documentation(info = "<html>
<p><strong>Model of a DC Machine with permanent magnets.</strong><br>
Armature resistance and inductance are modeled directly after the armature pins, then using a <em>AirGapDC</em> model. Permanent magnet excitation is modelled by a constant equivalent excitation current feeding AirGapDC. The machine models take the following loss effects into account:
</p>

<ul>
<li>heat losses in the temperature dependent armature winding resistance</li>
<li>brush losses in the armature circuit</li>
<li>friction losses</li>
<li>core losses (only eddy current losses, no hysteresis losses)</li>
<li>stray load losses</li>
</ul>

<p>No saturation is modelled.
<br><strong>Default values for machine's parameters (a realistic example) are:</strong><br></p>
<table>
<tr>
<td>stator's moment of inertia</td>
<td>0.29</td><td>kg.m2</td>
</tr>
<tr>
<td>rotor's moment of inertia</td>
<td>0.15</td><td>kg.m2</td>
</tr>
<tr>
<td>nominal armature voltage</td>
<td>100</td><td>V</td>
</tr>
<tr>
<td>nominal armature current</td>
<td>100</td><td>A</td>
</tr>
<tr>
<td>nominal speed</td>
<td>1425</td><td>rpm</td>
</tr>
<tr>
<td>nominal torque</td>
<td>63.66</td><td>Nm</td>
</tr>
<tr>
<td>nominal mechanical output</td>
<td>9.5</td><td>kW</td>
</tr>
<tr>
<td>efficiency</td>
<td>95.0</td><td>%</td>
</tr>
<tr>
<td>armature resistance</td>
<td>0.05</td><td>Ohm at reference temperature</td>
</tr>
<tr>
<td>reference temperature TaRef</td>
<td>20</td><td>&deg;C</td>
</tr>
<tr>
<td>temperature coefficient alpha20a </td>
<td>0</td><td>1/K</td>
</tr>
<tr>
<td>armature inductance</td>
<td>0.0015</td><td>H</td>
</tr>
<tr>
<td>armature nominal temperature TaNominal</td>
<td>20</td><td>&deg;C</td>
</tr>
<tr>
<td>armature operational temperature TaOperational</td>
<td>20</td><td>&deg;C</td>
</tr>
</table>
Armature resistance resp. inductance include resistance resp. inductance of commutating pole winding and compensation winding, if present.
</html>"));
      end Machines_U;
      model Machines "起发电机"
        extends Modelica.Electrical.Machines.Icons.TransientMachine;
        Machines_U dcpm
          annotation (Placement(transformation(origin = {-9.999999999999996, 3.552713678800501e-15}, 
            extent = {{-10.0, -10.0}, {10.0, 10.0}})));
        Modelica.Mechanics.Rotational.Interfaces.Flange_a flange "Shaft" 
          annotation (Placement(transformation(origin = {100.0, 0.0}, 
            extent = {{-10.0, -10.0}, {10.0, 10.0}})));



        Modelica.Electrical.Analog.Sensors.VoltageSensor voltageSensor
          annotation (Placement(transformation(origin = {19.999999999999996, 60.00000000000001}, 
            extent = {{10.0, -10.0}, {-10.0, 10.0}}, 
            rotation = -180.0)));
        Modelica.Electrical.Analog.Sensors.CurrentSensor currentSensor
          annotation (Placement(transformation(origin = {-3.9999999999999964, 40.0}, 
            extent = {{-10.0, -10.0}, {10.0, 10.0}}, 
            rotation = 180.0)));
        Modelica.Electrical.Analog.Interfaces.PositivePin p "positive pin" 
          annotation (Placement(transformation(origin = {-40.0, 100.0}, 
            extent = {{-10.0, -10.0}, {10.0, 10.0}})));
        Modelica.Electrical.Analog.Interfaces.NegativePin pin_an "Negative armature pin" 
          annotation (Placement(transformation(origin = {40.0, 100.00000000000003}, 
            extent = {{-10.0, -10.0}, {10.0, 10.0}})));
        Modelica.Blocks.Interfaces.RealOutput v "电压" 
          annotation (Placement(transformation(origin = {-60.2833734939759, 76.71903614457831}, 
            extent = {{-10.0, -10.0}, {10.0, 10.0}})));
        Modelica.Blocks.Interfaces.RealOutput i "电流" 
          annotation (Placement(transformation(origin = {-60.56674698795179, 53.88530120481929}, 
            extent = {{-10.0, -10.0}, {10.0, 10.0}})));
        Modelica.Blocks.Interfaces.RealOutput PE "发电机功率" 
          annotation (Placement(transformation(origin = {-60.0, 20.0}, 
            extent = {{-10.0, -10.0}, {10.0, 10.0}})));
        Modelica.Blocks.Interfaces.RealOutput n "转速" 
          annotation (Placement(transformation(origin = {-60.0, -20.0}, 
            extent = {{-10.0, -10.0}, {10.0, 10.0}})));
        Modelica.Blocks.Interfaces.RealOutput T "扭矩" 
          annotation (Placement(transformation(origin = {-60.0, -39.99999999999999}, 
            extent = {{-10.0, -10.0}, {10.0, 10.0}})));
        Modelica.Blocks.Interfaces.RealOutput PM "轴功率" 
          annotation (Placement(transformation(origin = {-60.0, -60.0}, 
            extent = {{-10.0, -10.0}, {10.0, 10.0}})));
        Modelica.Mechanics.Rotational.Sensors.SpeedSensor speedSensor
          annotation (Placement(transformation(origin = {60.0, -20.0}, 
            extent = {{-10.0, -10.0}, {10.0, 10.0}}, 
            rotation = -90.0)));
        Modelica.Mechanics.Rotational.Sensors.TorqueSensor torqueSensor
          annotation (Placement(transformation(origin = {28.0, 0.0}, 
            extent = {{-10.0, -10.0}, {10.0, 10.0}})));
      equation 
        PE = v * i;
        PM = T * n / 9550;

        connect(currentSensor.n, dcpm.pin_ap)
          annotation (Line(origin = {-11.0, 25.0}, 
            points = {{-3.0, 15.0}, {-8.0, 15.0}, {-8.0, -1.0}, {7.0, -1.0}, {7.0, -15.0}}, 
            color = {0, 0, 255}));
        connect(currentSensor.p, p)
          annotation (Line(origin = {-22.0, 75.0}, 
            points = {{28.0, -35.0}, {28.0, -18.0}, {-20.0, -18.0}, {-20.0, 25.0}, {-18.0, 25.0}}, 
            color = {0, 0, 255}));
        connect(voltageSensor.n, pin_an)
          annotation (Line(origin = {36.0, 80.0}, 
            points = {{-6.0, -20.0}, {2.0, -20.0}, {2.0, 20.0}, {4.0, 20.0}}, 
            color = {0, 0, 255}));
        connect(dcpm.pin_an, pin_an)
          annotation (Line(origin = {12.0, 55.0}, 
            points = {{-28.0, -45.0}, {-28.0, -35.0}, {30.0, -35.0}, {30.0, 45.0}, {28.0, 45.0}}, 
            color = {0, 0, 255}));
        connect(voltageSensor.p, p)
          annotation (Line(origin = {-15.0, 80.0}, 
            points = {{25.0, -20.0}, {-23.0, -20.0}, {-23.0, 20.0}, {-25.0, 20.0}}, 
            color = {0, 0, 255}));
        connect(voltageSensor.v, v)
          annotation (Line(origin = {-40.0, 66.0}, 
            points = {{60.0, 5.0}, {60.0, 11.0}, {-20.0, 11.0}}, 
            color = {0, 0, 127}));
        connect(currentSensor.i, i)
          annotation (Line(origin = {-37.0, 40.0}, 
            points = {{33.0, 11.0}, {33.0, 14.0}, {-24.0, 14.0}}, 
            color = {0, 0, 127}));
        connect(speedSensor.flange, flange)
          annotation (Line(origin = {80.0, -5.0}, 
            points = {{-20.0, -5.0}, {-20.0, 5.0}, {20.0, 5.0}}, 
            color = {0, 0, 0}));
        connect(speedSensor.w, n)
          annotation (Line(origin = {0.0, -30.0}, 
            points = {{60.0, -1.0}, {60.0, -7.0}, {-30.0, -7.0}, {-30.0, 10.0}, {-60.0, 10.0}}, 
            color = {0, 0, 127}));
        connect(torqueSensor.flange_a, dcpm.flange)
          annotation (Line(origin = {9.0, 0.0}, 
            points = {{9.0, 0.0}, {-9.0, 0.0}}, 
            color = {0, 0, 0}));
        connect(torqueSensor.flange_b, flange)
          annotation (Line(origin = {69.0, 0.0}, 
            points = {{-31.0, 0.0}, {31.0, 0.0}}, 
            color = {0, 0, 0}));
        connect(torqueSensor.tau, T)
          annotation (Line(origin = {-20.0, -25.0}, 
            points = {{40.0, 14.0}, {40.0, -15.0}, {-40.0, -15.0}}, 
            color = {0, 0, 127}));
      end Machines;
      model Machines_P "起发电机"
        extends Modelica.Electrical.Machines.Icons.TransientMachine;
        Machines_U dcpm
          annotation (Placement(transformation(origin = {-9.999999999999996, 3.552713678800501e-15}, 
            extent = {{-10.0, -10.0}, {10.0, 10.0}})));
        Modelica.Mechanics.Rotational.Interfaces.Flange_a flange "Shaft" 
          annotation (Placement(transformation(origin = {100.0, 0.0}, 
            extent = {{-10.0, -10.0}, {10.0, 10.0}})));



        Modelica.Electrical.Analog.Sensors.VoltageSensor voltageSensor
          annotation (Placement(transformation(origin = {19.999999999999996, 60.00000000000001}, 
            extent = {{10.0, -10.0}, {-10.0, 10.0}}, 
            rotation = -180.0)));
        Modelica.Electrical.Analog.Sensors.CurrentSensor currentSensor
          annotation (Placement(transformation(origin = {-3.9999999999999964, 40.0}, 
            extent = {{-10.0, -10.0}, {10.0, 10.0}}, 
            rotation = -450.0)));
        Modelica.Electrical.Analog.Interfaces.PositivePin p "positive pin" 
          annotation (Placement(transformation(origin = {-40.0, 100.0}, 
            extent = {{-10.0, -10.0}, {10.0, 10.0}})));
        Modelica.Electrical.Analog.Interfaces.NegativePin pin_an "Negative armature pin" 
          annotation (Placement(transformation(origin = {40.0, 100.00000000000003}, 
            extent = {{-10.0, -10.0}, {10.0, 10.0}})));
        Modelica.Blocks.Interfaces.RealOutput v "电压" 
          annotation (Placement(transformation(origin = {-60.0, 60.00000000000001}, 
            extent = {{-10.0, -10.0}, {10.0, 10.0}})));
        Modelica.Blocks.Interfaces.RealOutput i "电流" 
          annotation (Placement(transformation(origin = {-60.0, 40.0}, 
            extent = {{-10.0, -10.0}, {10.0, 10.0}})));
        Modelica.Blocks.Interfaces.RealOutput PE "发电机功率" 
          annotation (Placement(transformation(origin = {-60.0, 20.0}, 
            extent = {{-10.0, -10.0}, {10.0, 10.0}})));
        Modelica.Blocks.Interfaces.RealOutput n "转速" 
          annotation (Placement(transformation(origin = {-60.0, -20.0}, 
            extent = {{-10.0, -10.0}, {10.0, 10.0}})));
        Modelica.Blocks.Interfaces.RealOutput T "扭矩" 
          annotation (Placement(transformation(origin = {-60.0, -39.99999999999999}, 
            extent = {{-10.0, -10.0}, {10.0, 10.0}})));
        Modelica.Blocks.Interfaces.RealOutput PM "轴功率" 
          annotation (Placement(transformation(origin = {-60.0, -60.0}, 
            extent = {{-10.0, -10.0}, {10.0, 10.0}})));
        Modelica.Mechanics.Rotational.Sensors.SpeedSensor speedSensor
          annotation (Placement(transformation(origin = {60.0, -20.0}, 
            extent = {{-10.0, -10.0}, {10.0, 10.0}}, 
            rotation = -90.0)));
        Modelica.Mechanics.Rotational.Sensors.TorqueSensor torqueSensor
          annotation (Placement(transformation(origin = {28.0, 0.0}, 
            extent = {{-10.0, -10.0}, {10.0, 10.0}})));
        Modelica.Mechanics.Rotational.Sources.Speed speed
          annotation (Placement(transformation(origin = {66.0, 20.0}, 
            extent = {{-10.0, -10.0}, {10.0, 10.0}})));
      equation 
        PE = v * i;
        PM = T * n / 9550;
        speed.w_ref = v / 2;
        connect(currentSensor.n, dcpm.pin_ap)
          annotation (Line(origin = {-11.0, 25.0}, 
            points = {{7.0, 5.0}, {7.0, -15.0}}, 
            color = {0, 0, 255}));
        connect(currentSensor.p, p)
          annotation (Line(origin = {-22.0, 75.0}, 
            points = {{18.0, -25.0}, {18.0, -15.0}, {-18.0, -15.0}, {-18.0, 25.0}}, 
            color = {0, 0, 255}));
        connect(voltageSensor.n, pin_an)
          annotation (Line(origin = {36.0, 80.0}, 
            points = {{-6.0, -20.0}, {4.0, -20.0}, {4.0, 20.0}}, 
            color = {0, 0, 255}));
        connect(dcpm.pin_an, pin_an)
          annotation (Line(origin = {12.0, 55.0}, 
            points = {{-28.0, -45.0}, {-28.0, -35.0}, {28.0, -35.0}, {28.0, 45.0}}, 
            color = {0, 0, 255}));
        connect(voltageSensor.p, p)
          annotation (Line(origin = {-15.0, 80.0}, 
            points = {{25.0, -20.0}, {-25.0, -20.0}, {-25.0, 20.0}}, 
            color = {0, 0, 255}));
        connect(voltageSensor.v, v)
          annotation (Line(origin = {-40.0, 66.0}, 
            points = {{60.0, 5.0}, {58.0, 5.0}, {58.0, 6.0}, {-20.0, 6.0}, {-20.0, -6.0}}, 
            color = {0, 0, 127}));
        connect(currentSensor.i, i)
          annotation (Line(origin = {-37.0, 40.0}, 
            points = {{22.0, 0.0}, {-23.0, 0.0}}, 
            color = {0, 0, 127}));

        connect(speedSensor.w, n)
          annotation (Line(origin = {0.0, -30.0}, 
            points = {{60.0, -1.0}, {60.0, -2.0}, {-30.0, -2.0}, {-30.0, 10.0}, {-60.0, 10.0}}, 
            color = {0, 0, 127}));
        connect(torqueSensor.flange_a, dcpm.flange)
          annotation (Line(origin = {9.0, 0.0}, 
            points = {{9.0, 0.0}, {-9.0, 0.0}}, 
            color = {0, 0, 0}));
        connect(torqueSensor.tau, T)
          annotation (Line(origin = {-20.0, -25.0}, 
            points = {{40.0, 14.0}, {40.0, -15.0}, {-40.0, -15.0}}, 
            color = {0, 0, 127}));
        connect(speedSensor.flange, torqueSensor.flange_b)
          annotation (Line(origin = {49.0, -5.0}, 
            points = {{11.0, -5.0}, {11.0, 5.0}, {-11.0, 5.0}}, 
            color = {0, 0, 0}));
        connect(speed.flange, flange)
          annotation (Line(origin = {88.0, 10.0}, 
            points = {{-12.0, 10.0}, {0.0, 10.0}, {0.0, -10.0}, {12.0, -10.0}}, 
            color = {0, 0, 0}));
      end Machines_P;
    end Machines;
  end Battery;
  package APU "辅助动力装置"

    model APU "辅助动力装置"
      Modelica.Blocks.Interfaces.RealInput H "高度(m)" 
        annotation (Placement(transformation(origin = {-100.0, 44.0}, 
          extent = {{-10.0, -10.0}, {10.0, 10.0}})));
      Modelica.Blocks.Interfaces.RealOutput n "转速" 
        annotation (Placement(transformation(origin = {-59.99999999999998, -70.0}, 
          extent = {{-10.0, -10.0}, {10.0, 10.0}}, 
          rotation = -90.0)));
      Modelica.Blocks.Interfaces.RealInput V "表速(Ma)" 
        annotation (Placement(transformation(origin = {-100.0, 22.0}, 
          extent = {{-10.0, -10.0}, {10.0, 10.0}})));
      Modelica.Blocks.Interfaces.RealInput Control "起动控制" 
        annotation (Placement(transformation(origin = {-100.0, 0.0}, 
          extent = {{-10.0, -10.0}, {10.0, 10.0}})));
      Modelica.Mechanics.Rotational.Interfaces.Flange_a flange "Shaft" 
        annotation (Placement(transformation(origin = {100.0, -3.552713678800501e-15}, 
          extent = {{-10.0, -10.0}, {10.0, 10.0}})));
      annotation (Icon(coordinateSystem(extent = {{-100.0, -100.0}, {100.0, 100.0}}, 
        grid = {2.0, 2.0}), graphics = {Bitmap(origin = {0.0, 0.0}, 
        extent = {{-100.0, -100.0}, {100.0, 100.0}}, 
        fileName = "modelica://Modelica_Network/Resources/Images/engine.jpg", 
        imageSource = "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iAP0QpN4r4AA/wCCpoH/ADYB/wCXdWfrXwg/4KefGrUtJ0jWPi3+yj8FtFW6a4v/ABF4A8O6nr+shFglCQLZ6uhtZI3laPcQ8TqF3K5AaKQA/RDNIXAr4B/4d4ft1/8ASRT/AMwJ4f8A/j1Z3i3/AIJl/tw+NPC2p6Pd/wDBRa/gtNWtJbKeSw+CejWF2qSIUZoriC5SaGQBjtkidHQ4ZWDAGgD9EKTdXwD/AMOC8/8AN6f7f34fF/8A+5KztH/4Ngv2Wdd1LVtY+KNp8T/j14x1m7W5ufFXj/x1qNzrMiJBDBHA0tnJbI8aJCApkRnGSu8oqKoB9v8Axp/aG8Afs2+FbfXfiL448IeAdEu7tbCDUPEes2+lWs1wyO6wrLO6IZCkcjBQclY2OMA15j/w9h/ZZH/NyvwB/wDDhaR/8kV498L/APg2/wD2KPhB48sfEek/AXQLvUNP8wxxa3q2p65YvvjaM+ZaXtzNby4DkjzI22sFZcMqkevH/gk7+y2f+bavgAP+6eaR/wDI9AHgX/EUZ+wp/wBFy/8ALM8Q/wDyDWbb/wDByt8E/ib4qvdP+DHw5/aO/aNs9KtYLjVdS+G3w6ub+10h53mWKC4F01vMkjCB2H7oowOFdirqv6AeEvCWleAvCumaFoemafouiaLaRWGn6fYW6W9rYW8SBIoYokAWONEVVVVACgAAACr2w5PpngUAfnDL/wAFRP22Pi/4G1bV/hh/wT88QWlhqP26HwzqHjn4iabod+mySWKC4v8ARZ1huYssiu9v5y7l/wBXOVZZjo618IP+Cnfxr1LSdI1j4t/spfBbRFumuL/xF4B8O6nr+shFglCQLaauhtZI3laPcQ8TqF3K5AaKT9DdpJ9KcOBQB+eFt/wRY+Nvxe8VXmr/ABo/b2/aO166S0gstLj+Gv2b4c2ttGjzPIbi3tvPhuZGMigSbI3ATazSLsEZ4b/4Nb/2PV02SXxn4M8X/FPxVfXVzfar4r8VeNdVfWNcuJ55JnmuWtZ7eFpMyEFliUtgM5Zyzt+iFFAHjnww/wCCenwE+CHjyx8U+Cvgf8IfB/ibS/MNnq+h+DtO0+/tPMjaJ/LnhiWRN0bujYPKuyngkH17y2yfrwKlooAj2HP/ANanBf8AOKdRQA3b/nFGMH/69OooAbz/AJNABFOooAZtzS4p1FADcUu3NLRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFADSDmlxS0UAJijFLRQAmKMUtFACYoxS0UAJijFLRQAmKMUtFACYoxS0UAJijFLRQAmKMUtFACYpuw5p9FACDgUtFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFAH//Z")}));
      Modelica.Blocks.Interfaces.RealOutput T "扭矩" 
        annotation (Placement(transformation(origin = {-20.0, -70.0}, 
          extent = {{-10.0, -10.0}, {10.0, 10.0}}, 
          rotation = -90.0)));
      Modelica.Blocks.Interfaces.RealOutput P "功率" 
        annotation (Placement(transformation(origin = {19.99999999999998, -70.0}, 
          extent = {{-10.0, -10.0}, {10.0, 10.0}}, 
          rotation = -90.0)));
      Modelica.Blocks.Interfaces.RealOutput oil "油耗" 
        annotation (Placement(transformation(origin = {59.99999999999997, -70.00000000000001}, 
          extent = {{-10.0, -10.0}, {10.0, 10.0}}, 
          rotation = -90.0)));
      Modelica.Mechanics.Rotational.Sources.Torque torque
        annotation (Placement(transformation(extent = {{-10.0, -10.0}, {10.0, 10.0}})));
    equation 
      if Control <= 0.5 then 
        torque.tau = 0;
        n = 0;
        T = 0;
        P = 0;
        oil = 0;
      else
        torque.tau = 1 / H * 100000 + V * 10;
        n = torque.tau * 100;
        T = torque.tau * 1.5;
        P = n * T;
        oil = 0.81 - 1 / H * 100000;
      end if;
      connect(torque.flange, flange)
        annotation (Line(origin = {55.0, 0.0}, 
          points = {{-45.0, 0.0}, {45.0, 0.0}}, 
          color = {0, 0, 0}));
    end APU;
  end APU;
  package Pump "电动液压泵"
    import Modelica.Constants.*;
    import SI = Modelica.SIunits;
    import NonSI = Modelica.SIunits.Conversions.NonSIunits;

    model VariableDisplacementPump_ConstP2 "定压变量泵"

      /**********************************斜盘参数**********************************/
      // 结构参数
      parameter SI.Diameter diameter(displayUnit = "mm") = 0.1 "柱塞分布圆直径" 
        annotation (Dialog(tab = "斜盘", group = "结构参数"));

      // 斜盘角度
      parameter SI.Diameter Lactuator(displayUnit = "mm") = 0.06 "变量机构与斜盘转轴间距" 
        annotation (Dialog(tab = "斜盘", group = "斜盘角度"));
      parameter SI.Angle phimax = 0.349065850398866 "最大斜盘倾角" 
        annotation (Dialog(tab = "斜盘", group = "斜盘角度"));

      // 初始条件
      parameter SI.Angle phi0 = 0.349065850398866 "初始斜盘倾角" 
        annotation (Dialog(tab = "斜盘", group = "初始条件"));

      // 高级
      parameter Real fc = 0.1 "摩擦系数" 
        annotation (Dialog(tab = "斜盘", group = "高级"));
      /**********************************配油盘参数*********************************/
      // 结构参数
      parameter SI.Diameter diameter_orifice(displayUnit = "mm") = 0.011 "最大节流孔直径" 
        annotation (Dialog(tab = "配油盘", group = "结构参数"));

      // 节流孔参数
      parameter Real Cqmax(unit = "1") = 0.707 "配油盘节流孔流量系数" 
        annotation (Dialog(tab = "配油盘", group = "节流孔参数"));
      parameter Real[:,:] table_data = [0, 0; 10, 0.5; 15, 1; 175, 1; 180, 0; 360, 0] "配油盘节流孔吸油窗与排油窗分布" 
        annotation (Dialog(tab = "配油盘", group = "节流孔参数"));

      /***********************************柱塞************************************/
      // 结构参数
      parameter SI.Diameter ds(displayUnit = "mm") = 0.01 "筒径" 
        annotation (Dialog(tab = "柱塞", group = "结构参数"));
      parameter SI.Mass m = 1 "活塞质量" 
        annotation (Dialog(tab = "柱塞", group = "结构参数"));

      //泄露参数
      parameter Modelica.SIunits.Length dc(displayUnit = "mm") = 5e-6 "径向间隙 (0<dc<<ds)" 
        annotation (Dialog(tab = "柱塞", group = "泄漏"));
      parameter Real e = 0 "活塞的离心率" 
        annotation (Dialog(tab = "柱塞", group = "泄漏"));
      parameter SI.Pressure p_leak = 0 "泄漏点压力" 
        annotation (Dialog(tab = "柱塞", group = "泄漏"));

      /***********************************液压缸************************************/
      // 基本参数
      parameter SI.Diameter ds_cylinder(displayUnit = "mm") = 0.05 "活塞直径" 
        annotation (Dialog(tab = "液压缸", group = "基本参数"));
      parameter SI.Diameter dr_cylinder(displayUnit = "mm") = 0.02 "活塞杆直径" 
        annotation (Dialog(tab = "液压缸", group = "基本参数"));
      final parameter SI.Length L(displayUnit = "mm") = Lactuator * tan(phimax) "活塞行程" 
        annotation (Dialog(tab = "液压缸", group = "基本参数"));
      parameter SI.Mass m_cylinder = 0.5 "活塞质量" 
        annotation (Dialog(tab = "液压缸", group = "基本参数"));
      final parameter SI.Distance initialPosition(displayUnit = "mm") = Lactuator * (tan(phimax) - tan(phi0)) "活塞初始位置" 
        annotation (Dialog(tab = "液压缸", group = "初始参数"));

      // 高级
      parameter Boolean UseG = false "true，考虑泄漏||false，不考虑泄漏" 
        annotation (Dialog(tab = "液压缸", group = "高级"));
      parameter Modelica_Network.YJNY.Pump.Basic.Conductance G(displayUnit = "l/(min.bar)") = 4.2e-13 "泄漏率" 
        annotation (Dialog(tab = "液压缸", group = "高级", enable = UseG));
      parameter SI.TranslationalDampingConstant c = 2000 "活塞运动阻尼" 
        annotation (Dialog(tab = "液压缸", group = "高级"));
      parameter SI.Volume deadVolume = 2e-5 "死区容积" 
        annotation (Dialog(tab = "液压缸", group = "高级"));

      /***********************************控制阀************************************/
      // 结构参数
      parameter SI.Mass m_valve = 0.1 "阀芯质量" 
        annotation (Dialog(tab = "控制阀", group = "结构参数"));
      parameter SI.Length smax(displayUnit = "mm") = 0.01 "阀芯最大行程" 
        annotation (Dialog(tab = "控制阀", group = "结构参数"));
      parameter SI.Length ds_vavle(displayUnit = "mm") = 0.01 "筒径" 
        annotation (Dialog(tab = "控制阀", group = "结构参数"));
      parameter SI.Length dr_vavle(displayUnit = "mm") = 0.005 "杆径" 
        annotation (Dialog(tab = "控制阀", group = "结构参数"));
      parameter SI.Diameter Dh(displayUnit = "mm") = 0.005 "节流孔直径" 
        annotation (Dialog(tab = "控制阀", group = "结构参数"));

      // 弹簧参数
      parameter SI.Length s_0(displayUnit = "mm") = 0.01 "弹簧初始长度" 
        annotation (Dialog(tab = "控制阀", group = "弹簧参数"));
      parameter SI.TranslationalSpringConstant k = 1000 "弹簧刚度" 
        annotation (Dialog(tab = "控制阀", group = "弹簧参数"));
      final parameter SI.Force fspr0 = k * s_0 "弹簧预紧力";

      // 高级
      parameter Real F_prop(unit = "N.s/m", final min = 0) = 100 "阀芯滑动摩擦系数" 
        annotation (Dialog(tab = "控制阀", group = "高级"));
      parameter SI.Pressure p0 = 0 "回油点压力" 
        annotation (Dialog(tab = "控制阀", group = "高级"));

      /*************************************************************************/
      // 组件
      Basic.VariableDisplacementPump0 pump(
        ds = ds, 
        m = m, 
        dc = dc, 
        e = e, 
        p_leak = p_leak, 
        diameter_orifice = diameter_orifice, 
        Cqmax = Cqmax, 
        table_data = table_data, 
        diameter = diameter, 
        fc = fc, 
        Lactuator = Lactuator, 
        phimax = phimax, 
        swashActuationMech(phi(start = phi0)))
        annotation (Placement(transformation(origin = {-100.0, 0.0}, 
          extent = {{-40.0, -40.0}, {40.0, 40.0}})));
      Basic.SingleActingCylinder actingCylinder(
        D = ds_cylinder, 
        d = dr_cylinder, 
        L = L, 
        m = m_cylinder, 
        UseG = UseG, 
        G = G, 
        c = c, 
        deadVolume = deadVolume, 
        initialPosition = initialPosition) annotation (Placement(transformation(origin = {100.0, 0.0}, 
          extent = {{40.0, 40.0}, {-40.0, -40.0}})));

      Basic.ThreeWaysValve controlValve(
        m = m_valve, 
        smax = smax, 
        ds = ds_vavle, 
        Dh = Dh, 
        dr = dr_vavle, 
        s_0 = s_0, 
        k = k, 
        F_prop = F_prop) annotation (Placement(transformation(origin = {120, 100.0}, 
          extent = {{-40.0, -40.0}, {40.0, 40.0}})));

      Modelica.Mechanics.Translational.Sources.Position position(s(start = initialPosition))
        annotation (Placement(transformation(origin = {-30.0, 0.0}, 
          extent = {{10.0, -10.0}, {-10.0, 10.0}})));
      Basic.PositionSensor positionSensor annotation (Placement(transformation(origin = {30, 0.0}, 
        extent = {{10.0, -10.0}, {-10.0, 10.0}})));

      Basic.Tank tank(p0 = p0) annotation (Placement(transformation(origin = {190.0, 70.0}, 
        extent = {{-10.0, -10.0}, {10.0, 10.0}})));

      Basic.Fixed fixed annotation (Placement(transformation(origin = {190.0, -50.0}, 
        extent = {{-10.0, -10.0}, {10.0, 10.0}})));


      // 接口
      Modelica_Network.YJNY.Pump.Basic.PowerPort_a rotor "转轴接口" 
        annotation (Placement(transformation(origin = {-200.0, 0}, 
          extent = {{-10.0, -10.0}, {10.0, 10.0}})));
      Modelica_Network.YJNY.Pump.Basic.OilPort_b port_Out "泵出口" 
        annotation (Placement(transformation(origin = {0, 200.0}, 
          extent = {{-10.0, -10.0}, {10.0, 10.0}})));
      Modelica_Network.YJNY.Pump.Basic.OilPort_a port_In "泵入口" 
        annotation (Placement(transformation(origin = {0, -200.0}, 
          extent = {{-10.0, -10.0}, {10.0, 10.0}})));

      /*********************************监测量*************************************/
      SI.Angle phi = pump.phi "斜盘倾角";
      SI.Length s = actingCylinder.s "活塞位移";

      // 压力
      SI.Pressure p_in = pump.p_in "入口压力";
      SI.Pressure p_out = pump.p_out "出口压力";
      SI.Pressure p_chamber_1 = pump.p_chamber_1 "活塞腔#1压力";
      SI.Pressure p_chamber_2 = pump.p_chamber_2 "活塞腔#2压力";
      SI.Pressure p_chamber_3 = pump.p_chamber_3 "活塞腔#3压力";
      SI.Pressure p_chamber_4 = pump.p_chamber_4 "活塞腔#4压力";
      SI.Pressure p_chamber_5 = pump.p_chamber_5 "活塞腔#5压力";

      // 流量
      SI.VolumeFlowRate q_in(displayUnit = "l/min") = pump.q_in "入口流量";
      SI.VolumeFlowRate q_out(displayUnit = "l/min") = pump.q_out "出口流量";
      SI.VolumeFlowRate q_leak(displayUnit = "l/min") = pump.q_leak "泄漏流量";
      SI.VolumeFlowRate q_chamber_1(displayUnit = "l/min") = pump.q_chamber_1 "活塞#1流量";
      SI.VolumeFlowRate q_chamber_2(displayUnit = "l/min") = pump.q_chamber_2 "活塞#2流量";
      SI.VolumeFlowRate q_chamber_3(displayUnit = "l/min") = pump.q_chamber_3 "活塞#3流量";
      SI.VolumeFlowRate q_chamber_4(displayUnit = "l/min") = pump.q_chamber_4 "活塞#4流量";
      SI.VolumeFlowRate q_chamber_5(displayUnit = "l/min") = pump.q_chamber_5 "活塞#5流量";

      // 活塞位移
      SI.Length x_1 = pump.x_1 "活塞#1位移";
      SI.Length x_2 = pump.x_2 "活塞#2位移";
      SI.Length x_3 = pump.x_3 "活塞#3位移";
      SI.Length x_4 = pump.x_4 "活塞#4位移";
      SI.Length x_5 = pump.x_5 "活塞#5位移";

      // 速度
      SI.Velocity v_1 = pump.v_1 "活塞#1运动速度";
      SI.Velocity v_2 = pump.v_2 "活塞#2运动速度";
      SI.Velocity v_3 = pump.v_3 "活塞#3运动速度";
      SI.Velocity v_4 = pump.v_4 "活塞#4运动速度";
      SI.Velocity v_5 = pump.v_5 "活塞#5运动速度";

      // 活塞受力
      SI.Force f_1 = pump.f_1 "活塞#1受力";
      SI.Force f_2 = pump.f_2 "活塞#2受力";
      SI.Force f_3 = pump.f_3 "活塞#3受力";
      SI.Force f_4 = pump.f_4 "活塞#4受力";
      SI.Force f_5 = pump.f_5 "活塞#5受力";

      // 控制阀
      SI.Area area_orifice_P = controlValve.area_orifice_P "P腔阀孔通流面积";
      SI.Area area_orifice_T = controlValve.area_orifice_T "T腔阀孔通流面积";
      SI.Pressure pA = controlValve.pA "A口油压";
      SI.Pressure pP = controlValve.pP "P口油压";
      SI.Pressure pT = controlValve.pT "T口油压";
      SI.VolumeFlowRate qA = controlValve.qA "A口流量";
      SI.VolumeFlowRate qP = controlValve.qP "P口流量";
      SI.VolumeFlowRate qT = controlValve.qT "T口流量";
      annotation (Diagram(coordinateSystem(extent = {{-200.0, -200.0}, {200.0, 200.0}}, 
        grid = {2.0, 2.0})), 
        Icon(coordinateSystem(extent = {{-200.0, -200.0}, {200.0, 200.0}}, 
          grid = {2.0, 2.0}), graphics = {Rectangle(origin = {-147.0, 0.0}, 
          fillColor = {255, 255, 255}, 
          fillPattern = FillPattern.Solid, 
          extent = {{-53.0, 10.0}, {53.0, -10.0}}), Ellipse(origin = {0.0, 0.0}, 
          fillColor = {255, 255, 255}, 
          fillPattern = FillPattern.Solid, 
          extent = {{-100.0, 100.0}, {100.0, -100.0}}), Line(origin = {0.0, 150.0}, 
          points = {{0.0, -50.0}, {0.0, 50.0}}, 
          thickness = 1.0), Line(origin = {0.0, -150.0}, 
          points = {{0.0, -50.0}, {0.0, 50.0}}, 
          thickness = 1.0), Polygon(origin = {0.0, 78.00000000000003}, 
          fillColor = {0, 0, 0}, 
          fillPattern = FillPattern.Solid, 
          points = {{0.0, 20.0}, {-40.0, -20.0}, {40.0, -20.0}}), Line(origin = {0.0, 0.0}, 
          points = {{140.0, -140.0}, {-140.0, 140.0}}, 
          thickness = 1.0, 
          arrow = {Arrow.None, Arrow.Filled}, 
          arrowSize = 19.0), Line(origin = {170.0, -170.0}, 
          points = {{4.0, 30.0}, {-30.0, 20.0}, {30.0, 10.0}, {-30.0, 0.0}, {26.0, -8.0}, {-30.0, -20.0}, {10.0, -30.0}}, 
          thickness = 1.0), Rectangle(origin = {170.0, -90.0}, 
          fillColor = {255, 255, 255}, 
          fillPattern = FillPattern.Solid, 
          lineThickness = 1.0, 
          extent = {{-30.0, 50.0}, {30.0, -50.0}}), Polygon(origin = {170.0, -60.0}, 
          fillColor = {0, 85, 255}, 
          fillPattern = FillPattern.Solid, 
          points = {{-30.0, 20.0}, {30.0, 20.0}, {0.0, -20.0}}), Line(origin = {85.0, 60.0}, 
          points = {{85.0, -100.0}, {85.0, 100.0}, {-25.0, 100.0}, {-85.0, 40.0}}, 
          pattern = LinePattern.Dash, 
          thickness = 1.0), Text(origin = {0.0, 240.0}, 
          lineColor = {0, 128, 0}, 
          extent = {{-200.0, 40.0}, {200.0, -40.0}}, 
          textString = "%name", 
          fontName = "Times New Roman", 
          textStyle = {TextStyle.None}, 
          textColor = {0, 128, 0})}));
    equation 
      connect(actingCylinder.support, fixed.flange)
        annotation (Line(origin = {459.0, -89.00000000000001}, 
          points = {{-319.0, 69.0}, {-269.0, 69.0}, {-269.0, 39.0}}, 
          color = {0, 127, 0}));
      connect(pump.rotor, rotor)
        annotation (Line(origin = {-74.0, 0.0}, 
          points = {{-66.0, 0.0}, {-126.0, 0.0}}, 
          color = {0, 0, 0}));
      connect(controlValve.port_A, actingCylinder.port_A)
        annotation (Line(origin = {140.0, 52.0}, 
          points = {{-20.0, 28.0}, {-20.0, -28.0}}, 
          color = {0, 127, 255}));
      connect(pump.port_Out, port_Out)
        annotation (Line(origin = {-60.0, 120.0}, 
          points = {{-40.0, -80.0}, {-40.0, 0.0}, {60.0, 0.0}, {60.0, 80.0}}, 
          color = {0, 127, 255}));
      connect(pump.port_In, port_In)
        annotation (Line(origin = {-60.0, -120.0}, 
          points = {{-40.0, 80.0}, {-40.0, -80.0}, {60.0, -80.0}}, 
          color = {0, 127, 255}));
      connect(port_Out, controlValve.port_P)
        annotation (Line(origin = {60.0, 160.0}, 
          points = {{-60.0, 40.0}, {-60.0, -40.0}, {40.0, -40.0}}, 
          color = {0, 127, 255}));
      connect(actingCylinder.port_B, port_Out)
        annotation (Line(origin = {50.0, 112.0}, 
          points = {{30.0, -88.0}, {30.0, 8.0}, {-50.0, 8.0}, {-50.0, 88.0}}, 
          color = {0, 127, 255}));
      connect(tank.port, controlValve.port_T)
        annotation (Line(origin = {175.0, 95.0}, 
          points = {{15.0, -25.0}, {15.0, 25.0}, {-35.0, 25.0}}, 
          color = {0, 127, 255}));
      connect(pump.flange, position.flange)
        annotation (Line(origin = {-50.0, 0.0}, 
          points = {{-10.0, 0.0}, {10.0, 0.0}}, 
          color = {0, 127, 0}));
      connect(position.s_ref, positionSensor.s)
        annotation (Line(origin = {1.0, 0.0}, 
          points = {{-19.0, 0.0}, {18.0, 0.0}}, 
          color = {0, 0, 127}));
      connect(positionSensor.flange, actingCylinder.flange_b)
        annotation (Line(origin = {50.0, 0.0}, 
          points = {{-10.0, 0.0}, {10.0, 0.0}}, 
          color = {0, 127, 0}));
    end VariableDisplacementPump_ConstP2;
    model Test_VariableDisplacementPump_ConstP2
      annotation (experiment(Algorithm = Dassl, Interval = 0.01, StartTime = 0, StopTime = 60, Tolerance = 0.0001), 
        __MWorks(ResultViewerManager(resultViewers = {
          ResultViewer(name = "test", executeTrigger = executeTrigger.SimulationStarted, commands = {
          CreatePlot(id = 1, position = [0, 0, 697, 474], y = ["variableDisplacementPump_ConstP.port_In.q", "variableDisplacementPump_ConstP.port_Out.p"], x_display_unit = "s", y_display_units = ["l/min", "bar"], y_axis = [-1, 1], legend_layout = 7, legend_frame = True, left_title = "[bar]", right_title = "[l/min]", fix_time_range = True, fix_time_range_value = 6.95193e-310)})})));
      inner Basic.Oil oil annotation (Placement(transformation(origin = {-90.0, 70.0}, 
        extent = {{-10.0, -10.0}, {10.0, 10.0}})));

      Modelica_Network.YJNY.Pump.Basic.Tank tank3(p0 = 0)
        annotation (Placement(transformation(origin = {-1.4210854715202004e-14, -69.99999999999999}, 
          extent = {{-10.0, -10.0}, {10.0, 10.0}})));
      Modelica_Network.YJNY.Pump.Basic.BoundarySpeed_N boundarySpeed_N(N(displayUnit = "rpm") = 10.471975511966, 
        use_N_in = false) annotation (Placement(transformation(origin = {-80.0, 0.0}, 
          extent = {{-10.0, -10.0}, {10.0, 10.0}})));
      VariableDisplacementPump_ConstP2 variableDisplacementPump_ConstP(phi0 = 0.0349065850398866, 
        actingCylinder(volume_A(pstart
           = 0), 
          volume_B(pstart
             = 0)), 
        ds = 0.03) annotation (Placement(transformation(extent = {{-20.0, -20.0}, {20.0, 20.0}})));



      Modelica.Blocks.Sources.Trapezoid ramp(
        offset = 1e5, startTime = 10, 
        amplitude = 2e5, rising = 10, width = 10, falling = 10, 
        period = 40)


        annotation (Placement(transformation(origin = {50.0, 65.99999999999999}, 
          extent = {{10.0, -10.0}, {-10.0, 10.0}})));
      Basic.BoundaryPressure_oil boundaryPressure_oil(use_p_in = true) annotation (Placement(transformation(origin = {0.0, 59.999999999999986}, 
        extent = {{-10.0, -10.0}, {10.0, 10.0}}, 
        rotation = -90.0)));
    equation 
      connect(boundarySpeed_N.flange, variableDisplacementPump_ConstP.rotor)
        annotation (Line(origin = {-45.0, 0.0}, 
          points = {{-25.0, 0.0}, {25.0, 0.0}}, 
          color = {0, 0, 0}));
      connect(variableDisplacementPump_ConstP.port_In, tank3.port)
        annotation (Line(origin = {0.0, -45.0}, 
          points = {{0.0, 25.0}, {0.0, -25.0}}, 
          color = {0, 127, 255}));
      connect(variableDisplacementPump_ConstP.port_Out, boundaryPressure_oil.oilPort)
        annotation (Line(origin = {0.0, 34.0}, 
          points = {{0.0, -14.0}, {0.0, 17.0}}, 
          color = {0, 127, 255}));
      connect(boundaryPressure_oil.p_in, ramp.y)
        annotation (Line(origin = {25.0, 66.0}, 
          points = {{-15.0, 0.0}, {14.0, 0.0}}, 
          color = {0, 0, 127}));
    end Test_VariableDisplacementPump_ConstP2;
    package Basic

      connector PowerPort_a "转动接口a"
        extends PowerPort;
        annotation (
          defaultComponentName = "flange_a", 
          Documentation(info = "<html>
<p>
This is a connector for 1-dim. rotational mechanical systems and models
the mechanical flange of a shaft. The following variables are defined in this connector:
</p>

<table border=1 cellspacing=0 cellpadding=2>
  <tr><td valign=\"top\"> <b>phi</b></td>
      <td valign=\"top\"> Absolute rotation angle of the shaft flange in [rad] </td>
  </tr>
  <tr><td valign=\"top\"> <b>tau</b></td>
      <td valign=\"top\"> Cut-torque in the shaft flange in [Nm] </td>
  </tr>
</table>

<p>
There is a second connector for flanges: Flange_b. The connectors
Flange_a and Flange_b are completely identical. There is only a difference
in the icons, in order to easier identify a flange variable in a diagram.
For a discussion on the actual direction of the cut-torque tau and
of the rotation angle, see section
<a href=\"modelica://Modelica.Mechanics.Rotational.UsersGuide.SignConventions\">Sign Conventions</a>
in the user's guide of Rotational.
</p>

<p>
If needed, the absolute angular velocity w and the
absolute angular acceleration a of the flange can be determined by
differentiation of the flange angle phi:
</p>
<pre>
     w = der(phi);    a = der(w)
</pre>
</html>"), 
          Icon(coordinateSystem(preserveAspectRatio = true, extent = {{-100, -100}, {100, 100}}), graphics = {
            Ellipse(
            extent = {{-100, 100}, {100, -100}}, 
            lineColor = {0, 0, 0}, 
            fillColor = {95, 95, 95}, 
            fillPattern = FillPattern.Solid)}), 
          Diagram(coordinateSystem(
            preserveAspectRatio = true, 
            extent = {{-100, -100}, {100, 100}}), graphics = {Text(
            extent = {{-160, 90}, {40, 50}}, 
            lineColor = {0, 0, 0}, 
            textString = "%name"), Ellipse(
            extent = {{-40, 40}, {40, -40}}, 
            lineColor = {0, 0, 0}, 
            fillColor = {135, 135, 135}, 
            fillPattern = FillPattern.Solid)}));
      end PowerPort_a;
      connector PowerPort_b "转动接口b"
        extends PowerPort;
        annotation (
          defaultComponentName = "flange_b", 
          Documentation(info = "<html>
<p>
This is a connector for 1-dim. rotational mechanical systems and models
the mechanical flange of a shaft. The following variables are defined in this connector:
</p>

<table border=1 cellspacing=0 cellpadding=2>
  <tr><td valign=\"top\"> <b>phi</b></td>
      <td valign=\"top\"> Absolute rotation angle of the shaft flange in [rad] </td>
  </tr>
  <tr><td valign=\"top\"> <b>tau</b></td>
      <td valign=\"top\"> Cut-torque in the shaft flange in [Nm] </td>
  </tr>
</table>

<p>
There is a second connector for flanges: Flange_a. The connectors
Flange_a and Flange_b are completely identical. There is only a difference
in the icons, in order to easier identify a flange variable in a diagram.
For a discussion on the actual direction of the cut-torque tau and
of the rotation angle, see section
<a href=\"modelica://Modelica.Mechanics.Rotational.UsersGuide.SignConventions\">Sign Conventions</a>
in the user's guide of Rotational.
</p>

<p>
If needed, the absolute angular velocity w and the
absolute angular acceleration a of the flange can be determined by
differentiation of the flange angle phi:
</p>
<pre>
     w = der(phi);    a = der(w)
</pre>
</html>"), 
          Icon(coordinateSystem(
            preserveAspectRatio = true, 
            extent = {{-100, -100}, {100, 100}}), graphics = {Ellipse(
            extent = {{-98, 100}, {102, -100}}, 
            lineColor = {0, 0, 0}, 
            fillColor = {255, 255, 255}, 
            fillPattern = FillPattern.Solid)}), 
          Diagram(coordinateSystem(
            preserveAspectRatio = true, 
            extent = {{-100, -100}, {100, 100}}), graphics = {Ellipse(
            extent = {{-40, 40}, {40, -40}}, 
            lineColor = {0, 0, 0}, 
            fillColor = {255, 255, 255}, 
            fillPattern = FillPattern.Solid), Text(
            extent = {{-40, 90}, {160, 50}}, 
            lineColor = {0, 0, 0}, 
            textString = "%name")}));
      end PowerPort_b;
      connector PowerPort "基础机械接口"
        flow SI.Torque tau "传递扭矩";
        SI.Angle phi "转动角度";
      end PowerPort;
      model VariableDisplacementPump0 "可变排量泵"

        /**********************************斜盘参数**********************************/
        // 结构参数
        parameter SI.Diameter diameter(displayUnit = "mm") = 0.1 "柱塞分布圆直径" 
          annotation (Dialog(tab = "斜盘", group = "结构参数"));
        parameter Real fc = 0.1 "摩擦系数" 
          annotation (Dialog(tab = "斜盘", group = "结构参数"));

        // 斜盘角度
        parameter SI.Diameter Lactuator(displayUnit = "mm") = 0.06 "变量机构与斜盘转轴间距" 
          annotation (Dialog(tab = "斜盘", group = "斜盘角度"));
        parameter SI.Angle phimax = 0.349065850398866 "最大斜盘倾角" 
          annotation (Dialog(tab = "斜盘", group = "斜盘角度"));

        /**********************************配油盘参数*********************************/
        // 结构参数
        parameter SI.Diameter diameter_orifice(displayUnit = "mm") = 0.011 "最大节流孔直径" 
          annotation (Dialog(tab = "配油盘", group = "结构参数"));

        // 节流孔参数
        parameter Real Cqmax(unit = "1") = 0.707 "配油盘节流孔流量系数" 
          annotation (Dialog(tab = "配油盘", group = "节流孔参数"));
        parameter Real[:,:] table_data = [0, 0; 10, 0.5; 15, 1; 175, 1; 180, 0; 360, 0] "配油盘节流孔吸油窗与排油窗分布" 
          annotation (Dialog(tab = "配油盘", group = "节流孔参数"));

        /***********************************柱塞************************************/
        // 结构参数
        parameter SI.Diameter ds(displayUnit = "mm") = 0.01 "筒径" 
          annotation (Dialog(tab = "柱塞", group = "结构参数"));
        parameter SI.Mass m = 1 "活塞质量" 
          annotation (Dialog(tab = "柱塞", group = "结构参数"));

        //泄露参数
        parameter Modelica.SIunits.Length dc(displayUnit = "mm") = 5e-6 "径向间隙 (0<dc<<ds)" 
          annotation (Dialog(tab = "柱塞", group = "泄漏"));
        parameter Real e = 0 "活塞的离心率" 
          annotation (Dialog(tab = "柱塞", group = "泄漏"));
        parameter SI.Pressure p_leak = 0 "泄漏点压力" 
          annotation (Dialog(tab = "柱塞", group = "泄漏"));

        /*************************************************************************/
        // 柱塞#1
        Basic.SwashPlate swashPlate_1(
          diameter = diameter, 
          fc = fc)
          "斜盘#1" 
          annotation (Placement(transformation(origin = {-120, 160.0}, 
            extent = {{20.0, -20.0}, {-20.0, 20.0}})));
        FixedBodyPiston fixedBodyPiston_1(
          m = m, 
          ds = ds) "活塞#1" annotation (Placement(transformation(origin = {40.0, 160.0}, 
            extent = {{-20.0, -20.0}, {20.0, 20.0}})));


        ViscousFrictionAndLeakagePiston viscousFrictionAndLeakageSpool_1(
          dc = dc, 
          ds = ds, 
          e = e) "活塞泄漏模型#1" annotation (Placement(transformation(origin = {-40.0, 160.0}, 
            extent = {{-20.0, -20.0}, {20.0, 20.0}})));


        Basic.ValvePlate valvePlate_1(
          diameter_orifice = diameter_orifice, 
          Cqmax = Cqmax, 
          table_data = table_data)
          "配流盘#1" 
          annotation (Placement(transformation(origin = {120, 160.0}, 
            extent = {{20.0, -20.0}, {-20.0, 20.0}})));
        CompressVolume compressVolume_1 "可压缩体积#1" annotation (Placement(transformation(origin = {80.0, 190.0}, 
          extent = {{-10.0, -10.0}, {10.0, 10.0}})));



        // 柱塞#2
        Basic.SwashPlate swashPlate_2(
          diameter = diameter, 
          fc = fc, 
          theta_0 = 1.25663706143592)
          "斜盘#2" 
          annotation (Placement(transformation(origin = {-120, 80}, 
            extent = {{20.0, -20.0}, {-20.0, 20.0}})));
        FixedBodyPiston fixedBodyPiston_2(
          m = m, 
          ds = ds) "活塞#2" annotation (Placement(transformation(origin = {40.0, 80}, 
            extent = {{-20.0, -20.0}, {20.0, 20.0}})));


        ViscousFrictionAndLeakagePiston viscousFrictionAndLeakageSpool_2(
          dc = dc, 
          ds = ds, 
          e = e)
          "活塞泄漏模型#2" 
          annotation (Placement(transformation(origin = {-40.0, 80}, 
            extent = {{-20.0, -20.0}, {20.0, 20.0}})));
        Basic.ValvePlate valvePlate_2(
          diameter_orifice = diameter_orifice, 
          Cqmax = Cqmax, 
          table_data = table_data)
          "配流盘#2" 
          annotation (Placement(transformation(origin = {120, 80}, 
            extent = {{20.0, -20.0}, {-20.0, 20.0}})));
        CompressVolume compressVolume_2 "可压缩体积#2" annotation (Placement(transformation(origin = {80.0, 110.0}, 
          extent = {{-10.0, -10.0}, {10.0, 10.0}})));



        // 柱塞#3
        Basic.SwashPlate swashPlate_3(
          diameter = diameter, 
          fc = fc, 
          theta_0 = 2.51327412287183)
          "斜盘#3" 
          annotation (Placement(transformation(origin = {-120, 0}, 
            extent = {{20.0, -20.0}, {-20.0, 20.0}})));
        FixedBodyPiston fixedBodyPiston_3(
          m = m, 
          ds = ds) "活塞#3" annotation (Placement(transformation(origin = {40.0, 0.0}, 
            extent = {{-20.0, -20.0}, {20.0, 20.0}})));


        ViscousFrictionAndLeakagePiston viscousFrictionAndLeakageSpool_3(
          dc = dc, 
          ds = ds, 
          e = e)
          "活塞泄漏模型#3" 
          annotation (Placement(transformation(origin = {-40.0, 0}, 
            extent = {{-20.0, -20.0}, {20.0, 20.0}})));
        Basic.ValvePlate valvePlate_3(
          diameter_orifice = diameter_orifice, 
          Cqmax = Cqmax, 
          table_data = table_data)
          "配流盘#3" 
          annotation (Placement(transformation(origin = {120.0, 0.0}, 
            extent = {{20.0, -20.0}, {-20.0, 20.0}})));
        CompressVolume compressVolume_3 "可压缩体积#3" annotation (Placement(transformation(origin = {80.0, 30.0}, 
          extent = {{-10.0, -10.0}, {10.0, 10.0}})));



        // 柱塞#4
        Basic.SwashPlate swashPlate_4(
          diameter = diameter, 
          fc = fc, 
          theta_0 = 3.76991118430775)
          "斜盘#4" 
          annotation (Placement(transformation(origin = {-120, -80}, 
            extent = {{20.0, -20.0}, {-20.0, 20.0}})));
        FixedBodyPiston fixedBodyPiston_4(
          m = m, 
          ds = ds) "活塞#4" annotation (Placement(transformation(origin = {40.0, -80}, 
            extent = {{-20.0, -20.0}, {20.0, 20.0}})));


        ViscousFrictionAndLeakagePiston viscousFrictionAndLeakageSpool_4(
          dc = dc, 
          ds = ds, 
          e = e)
          "活塞泄漏模型#4" 
          annotation (Placement(transformation(origin = {-40.0, -80}, 
            extent = {{-20.0, -20.0}, {20.0, 20.0}})));
        Basic.ValvePlate valvePlate_4(
          diameter_orifice = diameter_orifice, 
          Cqmax = Cqmax, 
          table_data = table_data)
          "配流盘#4" 
          annotation (Placement(transformation(origin = {120, -80}, 
            extent = {{20.0, -20.0}, {-20.0, 20.0}})));
        CompressVolume compressVolume_4 "可压缩体积#4" annotation (Placement(transformation(origin = {80.0, -50.0}, 
          extent = {{-10.0, -10.0}, {10.0, 10.0}})));



        // 柱塞#5
        Basic.SwashPlate swashPlate_5(
          diameter = diameter, 
          fc = fc, 
          theta_0 = 5.02654824574367)
          "斜盘#5" 
          annotation (Placement(transformation(origin = {-120, -160}, 
            extent = {{20.0, -20.0}, {-20.0, 20.0}})));
        FixedBodyPiston fixedBodyPiston_5(
          m = m, 
          ds = ds) "活塞#5" annotation (Placement(transformation(origin = {40.0, -160}, 
            extent = {{-20.0, -20.0}, {20.0, 20.0}})));


        ViscousFrictionAndLeakagePiston viscousFrictionAndLeakageSpool_5(
          dc = dc, 
          ds = ds, 
          e = e)
          "活塞泄漏模型#5" 
          annotation (Placement(transformation(origin = {-40.0, -160}, 
            extent = {{-20.0, -20.0}, {20.0, 20.0}})));
        Basic.ValvePlate valvePlate_5(
          diameter_orifice = diameter_orifice, 
          Cqmax = Cqmax, 
          table_data = table_data)
          "配流盘#5" 
          annotation (Placement(transformation(origin = {120, -160}, 
            extent = {{20.0, -20.0}, {-20.0, 20.0}})));
        CompressVolume compressVolume_5 "可压缩体积#5" annotation (Placement(transformation(origin = {80.0, -130.0}, 
          extent = {{-10.0, -10.0}, {10.0, 10.0}})));



        // 组件
        Fixed fixed_Trans annotation (Placement(transformation(origin = {190.0, -190.0}, 
          extent = {{-10.0, -10.0}, {10.0, 10.0}})));


        Modelica_Network.YJNY.Pump.Basic.Tank tank(p0 = p_leak)
          annotation (Placement(transformation(origin = {-180.0, -190.0}, 
            extent = {{-10.0, -10.0}, {10.0, 10.0}})));

        SwashActuationMech2 swashActuationMech(
          Lactuator = Lactuator, 
          phimax = phimax)
          "斜盘变量机构" annotation (Placement(transformation(origin = {170.0, 0.0}, 
            extent = {{10.0, 10.0}, {-10.0, -10.0}})));

        // 接口
        Modelica_Network.YJNY.Pump.Basic.PowerPort_a rotor "转轴接口" 
          annotation (Placement(transformation(origin = {-200.0, 0}, 
            extent = {{-10.0, -10.0}, {10.0, 10.0}})));
        Modelica_Network.YJNY.Pump.Basic.OilPort_b port_Out "泵出口" 
          annotation (Placement(transformation(origin = {0, 200.0}, 
            extent = {{-10.0, -10.0}, {10.0, 10.0}})));
        Modelica_Network.YJNY.Pump.Basic.OilPort_a port_In "泵入口" 
          annotation (Placement(transformation(origin = {0, -200.0}, 
            extent = {{-10.0, -10.0}, {10.0, 10.0}})));
        Modelica_Network.YJNY.Pump.Basic.Flange_a flange
          annotation (Placement(transformation(origin = {200.0, 0.0}, 
            extent = {{-10.0, -10.0}, {10.0, 10.0}})));

        /*********************************监测量*************************************/
        // 斜盘倾角
        SI.Angle phi = swashActuationMech.phi "斜盘倾角";

        // 压力
        SI.Pressure p_in = port_In.p "入口压力";
        SI.Pressure p_out = port_Out.p "出口压力";
        SI.Pressure p_chamber_1 = valvePlate_1.p_chamber "活塞腔#1压力";
        SI.Pressure p_chamber_2 = valvePlate_2.p_chamber "活塞腔#2压力";
        SI.Pressure p_chamber_3 = valvePlate_3.p_chamber "活塞腔#3压力";
        SI.Pressure p_chamber_4 = valvePlate_4.p_chamber "活塞腔#4压力";
        SI.Pressure p_chamber_5 = valvePlate_5.p_chamber "活塞腔#5压力";

        // 流量
        SI.VolumeFlowRate q_in(displayUnit = "l/min") = port_In.q "入口流量";
        SI.VolumeFlowRate q_out(displayUnit = "l/min") = port_Out.q "出口流量";
        SI.VolumeFlowRate q_leak(displayUnit = "l/min") = tank.port.q "泄漏流量";
        SI.VolumeFlowRate q_chamber_1(displayUnit = "l/min") = valvePlate_1.q_out "活塞#1流量";
        SI.VolumeFlowRate q_chamber_2(displayUnit = "l/min") = valvePlate_2.q_out "活塞#2流量";
        SI.VolumeFlowRate q_chamber_3(displayUnit = "l/min") = valvePlate_3.q_out "活塞#3流量";
        SI.VolumeFlowRate q_chamber_4(displayUnit = "l/min") = valvePlate_4.q_out "活塞#4流量";
        SI.VolumeFlowRate q_chamber_5(displayUnit = "l/min") = valvePlate_5.q_out "活塞#5流量";

        // 活塞位移
        SI.Length x_1 = fixedBodyPiston_1.x "活塞#1位移";
        SI.Length x_2 = fixedBodyPiston_2.x "活塞#2位移";
        SI.Length x_3 = fixedBodyPiston_3.x "活塞#3位移";
        SI.Length x_4 = fixedBodyPiston_4.x "活塞#4位移";
        SI.Length x_5 = fixedBodyPiston_5.x "活塞#5位移";

        // 速度
        SI.Velocity v_1 = fixedBodyPiston_1.v "活塞#1运动速度";
        SI.Velocity v_2 = fixedBodyPiston_2.v "活塞#2运动速度";
        SI.Velocity v_3 = fixedBodyPiston_3.v "活塞#3运动速度";
        SI.Velocity v_4 = fixedBodyPiston_4.v "活塞#4运动速度";
        SI.Velocity v_5 = fixedBodyPiston_5.v "活塞#5运动速度";

        // 活塞受力
        SI.Force f_1 = fixedBodyPiston_1.f "活塞#1受力";
        SI.Force f_2 = fixedBodyPiston_2.f "活塞#2受力";
        SI.Force f_3 = fixedBodyPiston_3.f "活塞#3受力";
        SI.Force f_4 = fixedBodyPiston_4.f "活塞#4受力";
        SI.Force f_5 = fixedBodyPiston_5.f "活塞#5受力";
        annotation (Diagram(coordinateSystem(extent = {{-200.0, -200.0}, {200.0, 200.0}}, 
          grid = {2.0, 2.0})), 
          Icon(coordinateSystem(extent = {{-200.0, -200.0}, {200.0, 200.0}}, 
            grid = {2.0, 2.0}), graphics = {Rectangle(origin = {-147.0, 0.0}, 
            fillColor = {255, 255, 255}, 
            fillPattern = FillPattern.Solid, 
            extent = {{-53.0, 10.0}, {53.0, -10.0}}), Rectangle(origin = {145.0, -4.440892098500626e-16}, 
            fillColor = {255, 255, 255}, 
            fillPattern = FillPattern.Solid, 
            extent = {{-53.0, 10.0}, {53.0, -10.0}}), Ellipse(origin = {0.0, 0.0}, 
            fillColor = {255, 255, 255}, 
            fillPattern = FillPattern.Solid, 
            extent = {{-100.0, 100.0}, {100.0, -100.0}}), Line(origin = {0.0, 150.0}, 
            points = {{0.0, -50.0}, {0.0, 50.0}}, 
            thickness = 1.0), Line(origin = {0.0, -150.0}, 
            points = {{0.0, -50.0}, {0.0, 50.0}}, 
            thickness = 1.0), Polygon(origin = {0.0, 78.00000000000003}, 
            fillColor = {0, 0, 0}, 
            fillPattern = FillPattern.Solid, 
            points = {{0.0, 20.0}, {-40.0, -20.0}, {40.0, -20.0}}), Line(origin = {30.0, 0.0}, 
            points = {{170.0, -140.0}, {110.0, -140.0}, {-170.0, 140.0}}, 
            thickness = 1.0, 
            arrow = {Arrow.None, Arrow.Filled}, 
            arrowSize = 19.0), Line(origin = {150.0, -140.0}, 
            points = {{10.0, 10.0}, {-10.0, 0.0}, {10.0, -10.0}}, 
            thickness = 1.0)}));
      equation 
        connect(swashActuationMech.flange, flange)
          annotation (Line(origin = {191.0, 0.0}, 
            points = {{-11.0, 0.0}, {9.0, 0.0}}, 
            color = {0, 127, 0}));
        connect(swashPlate_1.flange_a, viscousFrictionAndLeakageSpool_1.flange_a)
          annotation (Line(origin = {-80.0, 160.0}, 
            points = {{-20.0, 0.0}, {20.0, 0.0}}, 
            color = {0, 127, 0}));
        connect(viscousFrictionAndLeakageSpool_1.flange_b, fixedBodyPiston_1.flange_a)
          annotation (Line(origin = {0.0, 160.0}, 
            points = {{-20.0, 0.0}, {20.0, 0.0}}, 
            color = {0, 127, 0}));
        connect(viscousFrictionAndLeakageSpool_1.support_b, fixedBodyPiston_1.support_a)
          annotation (Line(origin = {0.0, 140.0}, 
            points = {{-20.0, 0.0}, {20.0, 0.0}}, 
            color = {0, 127, 0}));
        connect(swashPlate_1.angle, valvePlate_1.rotorangle)
          annotation (Line(origin = {0.0, 130.0}, 
            points = {{-98.0, 10.0}, {-80.0, 10.0}, {-80.0, 0.0}, {80.0, 0.0}, {80.0, 10.0}, {98.0, 10.0}}, 
            color = {0, 0, 127}));
        connect(swashPlate_3.angle, valvePlate_3.rotorangle)
          annotation (Line(origin = {0.0, -30.0}, 
            points = {{-98.0, 10.0}, {-80.0, 10.0}, {-80.0, 0.0}, {80.0, 0.0}, {80.0, 10.0}, {98.0, 10.0}}, 
            color = {0, 0, 127}));
        connect(swashPlate_4.angle, valvePlate_4.rotorangle)
          annotation (Line(origin = {0.0, -110.0}, 
            points = {{-98.0, 10.0}, {-80.0, 10.0}, {-80.0, 0.0}, {80.0, 0.0}, {80.0, 10.0}, {98.0, 10.0}}, 
            color = {0, 0, 127}));
        connect(swashPlate_5.angle, valvePlate_5.rotorangle)
          annotation (Line(origin = {0.0, -190.0}, 
            points = {{-98.0, 10.0}, {-80.0, 10.0}, {-80.0, 0.0}, {80.0, 0.0}, {80.0, 10.0}, {98.0, 10.0}}, 
            color = {0, 0, 127}));
        connect(viscousFrictionAndLeakageSpool_5.port_B, tank.port)
          annotation (Line(origin = {-115.0, -185.0}, 
            points = {{65.0, 5.0}, {65.0, -5.0}, {-65.0, -5.0}}, 
            color = {0, 127, 255}));
        connect(viscousFrictionAndLeakageSpool_1.port_B, tank.port)
          annotation (Line(origin = {-115.0, -25.0}, 
            points = {{65.0, 165.0}, {65.0, 155.0}, {-65.0, 155.0}, {-65.0, -165.0}}, 
            color = {0, 127, 255}));
        connect(viscousFrictionAndLeakageSpool_1.port_A, valvePlate_1.port_chamber)
          annotation (Line(origin = {35.0, 150.0}, 
            points = {{-65.0, -10.0}, {-65.0, -20.0}, {45.0, -20.0}, {45.0, 20.0}, {65.0, 20.0}}, 
            color = {0, 127, 255}));
        connect(fixedBodyPiston_1.port_a, valvePlate_1.port_chamber)
          annotation (Line(origin = {75.0, 150.0}, 
            points = {{-25.0, -10.0}, {-25.0, -20.0}, {5.0, -20.0}, {5.0, 20.0}, {25.0, 20.0}}, 
            color = {0, 127, 255}));
        connect(viscousFrictionAndLeakageSpool_2.port_B, tank.port)
          annotation (Line(origin = {-115.0, -65.0}, 
            points = {{65.0, 125.0}, {65.0, 115.0}, {-65.0, 115.0}, {-65.0, -125.0}}, 
            color = {0, 127, 255}));
        connect(viscousFrictionAndLeakageSpool_3.port_B, tank.port)
          annotation (Line(origin = {-115.0, -105.0}, 
            points = {{65.0, 85.0}, {65.0, 75.0}, {-65.0, 75.0}, {-65.0, -85.0}}, 
            color = {0, 127, 255}));
        connect(viscousFrictionAndLeakageSpool_4.port_B, tank.port)
          annotation (Line(origin = {-115.0, -145.0}, 
            points = {{65.0, 45.0}, {65.0, 35.0}, {-65.0, 35.0}, {-65.0, -45.0}}, 
            color = {0, 127, 255}));
        connect(swashPlate_2.angle, valvePlate_2.rotorangle)
          annotation (Line(origin = {0.0, 55.0}, 
            points = {{-98.0, 5.0}, {-80.0, 5.0}, {-80.0, -5.0}, {80.0, -5.0}, {80.0, 5.0}, {98.0, 5.0}}, 
            color = {0, 0, 127}));
        connect(viscousFrictionAndLeakageSpool_5.port_A, valvePlate_5.port_chamber)
          annotation (Line(origin = {35.0, -170.0}, 
            points = {{-65.0, -10.0}, {-65.0, -20.0}, {45.0, -20.0}, {45.0, 20.0}, {65.0, 20.0}}, 
            color = {0, 127, 255}));
        connect(fixedBodyPiston_5.port_a, valvePlate_5.port_chamber)
          annotation (Line(origin = {75.0, -170.0}, 
            points = {{-25.0, -10.0}, {-25.0, -20.0}, {5.0, -20.0}, {5.0, 20.0}, {25.0, 20.0}}, 
            color = {0, 127, 255}));
        connect(viscousFrictionAndLeakageSpool_4.port_A, valvePlate_4.port_chamber)
          annotation (Line(origin = {35.0, -90.0}, 
            points = {{-65.0, -10.0}, {-65.0, -20.0}, {45.0, -20.0}, {45.0, 20.0}, {65.0, 20.0}}, 
            color = {0, 127, 255}));
        connect(fixedBodyPiston_4.port_a, valvePlate_4.port_chamber)
          annotation (Line(origin = {75.0, -90.0}, 
            points = {{-25.0, -10.0}, {-25.0, -20.0}, {5.0, -20.0}, {5.0, 20.0}, {25.0, 20.0}}, 
            color = {0, 127, 255}));
        connect(viscousFrictionAndLeakageSpool_3.port_A, valvePlate_3.port_chamber)
          annotation (Line(origin = {35.0, -10.0}, 
            points = {{-65.0, -10.0}, {-65.0, -20.0}, {45.0, -20.0}, {45.0, 20.0}, {65.0, 20.0}}, 
            color = {0, 127, 255}));
        connect(fixedBodyPiston_3.port_a, valvePlate_3.port_chamber)
          annotation (Line(origin = {75.0, -10.0}, 
            points = {{-25.0, -10.0}, {-25.0, -20.0}, {5.0, -20.0}, {5.0, 20.0}, {25.0, 20.0}}, 
            color = {0, 127, 255}));
        connect(viscousFrictionAndLeakageSpool_2.port_A, valvePlate_2.port_chamber)
          annotation (Line(origin = {35.0, 70.0}, 
            points = {{-65.0, -10.0}, {-65.0, -20.0}, {45.0, -20.0}, {45.0, 20.0}, {65.0, 20.0}}, 
            color = {0, 127, 255}));
        connect(fixedBodyPiston_2.port_a, valvePlate_2.port_chamber)
          annotation (Line(origin = {75.0, 70.0}, 
            points = {{-25.0, -10.0}, {-25.0, -20.0}, {5.0, -20.0}, {5.0, 20.0}, {25.0, 20.0}}, 
            color = {0, 127, 255}));
        connect(compressVolume_1.port_A, valvePlate_1.port_chamber)
          annotation (Line(origin = {90.0, 175.0}, 
            points = {{-10.0, 5.0}, {-10.0, -5.0}, {10.0, -5.0}}, 
            color = {0, 127, 255}));
        connect(valvePlate_2.port_chamber, compressVolume_2.port_A)
          annotation (Line(origin = {90.0, 95.0}, 
            points = {{10.0, -5.0}, {-10.0, -5.0}, {-10.0, 5.0}}, 
            color = {0, 127, 255}));
        connect(valvePlate_3.port_chamber, compressVolume_3.port_A)
          annotation (Line(origin = {90.0, 15.0}, 
            points = {{10.0, -5.0}, {-10.0, -5.0}, {-10.0, 5.0}}, 
            color = {0, 127, 255}));
        connect(valvePlate_4.port_chamber, compressVolume_4.port_A)
          annotation (Line(origin = {90.0, -65.0}, 
            points = {{10.0, -5.0}, {-10.0, -5.0}, {-10.0, 5.0}}, 
            color = {0, 127, 255}));
        connect(valvePlate_5.port_chamber, compressVolume_5.port_A)
          annotation (Line(origin = {90.0, -145.0}, 
            points = {{10.0, -5.0}, {-10.0, -5.0}, {-10.0, 5.0}}, 
            color = {0, 127, 255}));
        connect(swashPlate_5.flange_a, viscousFrictionAndLeakageSpool_5.flange_a)
          annotation (Line(origin = {-80.0, -160.0}, 
            points = {{-20.0, 0.0}, {20.0, 0.0}}, 
            color = {0, 127, 0}));
        connect(viscousFrictionAndLeakageSpool_5.flange_b, fixedBodyPiston_5.flange_a)
          annotation (Line(origin = {0.0, -160.0}, 
            points = {{-20.0, 0.0}, {20.0, 0.0}}, 
            color = {0, 127, 0}));
        connect(viscousFrictionAndLeakageSpool_5.support_b, fixedBodyPiston_5.support_a)
          annotation (Line(origin = {0.0, -180.0}, 
            points = {{-20.0, 0.0}, {20.0, 0.0}}, 
            color = {0, 127, 0}));
        connect(viscousFrictionAndLeakageSpool_4.support_b, fixedBodyPiston_4.support_a)
          annotation (Line(origin = {0.0, -100.0}, 
            points = {{-20.0, 0.0}, {20.0, 0.0}}, 
            color = {0, 127, 0}));
        connect(viscousFrictionAndLeakageSpool_4.flange_b, fixedBodyPiston_4.flange_a)
          annotation (Line(origin = {0.0, -80.0}, 
            points = {{-20.0, 0.0}, {20.0, 0.0}}, 
            color = {0, 127, 0}));
        connect(viscousFrictionAndLeakageSpool_4.flange_a, swashPlate_4.flange_a)
          annotation (Line(origin = {-80.0, -80.0}, 
            points = {{20.0, 0.0}, {-20.0, 0.0}}, 
            color = {0, 127, 0}));
        connect(viscousFrictionAndLeakageSpool_3.support_b, fixedBodyPiston_3.support_a)
          annotation (Line(origin = {0.0, -20.0}, 
            points = {{-20.0, 0.0}, {20.0, 0.0}}, 
            color = {0, 127, 0}));
        connect(fixedBodyPiston_3.flange_a, viscousFrictionAndLeakageSpool_3.flange_b)
          annotation (Line(origin = {0.0, 0.0}, 
            points = {{20.0, 0.0}, {-20.0, 0.0}}, 
            color = {0, 127, 0}));
        connect(viscousFrictionAndLeakageSpool_3.flange_a, swashPlate_3.flange_a)
          annotation (Line(origin = {-80.0, 0.0}, 
            points = {{20.0, 0.0}, {-20.0, 0.0}}, 
            color = {0, 127, 0}));
        connect(viscousFrictionAndLeakageSpool_2.support_b, fixedBodyPiston_2.support_a)
          annotation (Line(origin = {0.0, 60.0}, 
            points = {{-20.0, 0.0}, {20.0, 0.0}}, 
            color = {0, 127, 0}));
        connect(fixedBodyPiston_2.flange_a, viscousFrictionAndLeakageSpool_2.flange_b)
          annotation (Line(origin = {0.0, 80.0}, 
            points = {{20.0, 0.0}, {-20.0, 0.0}}, 
            color = {0, 127, 0}));
        connect(viscousFrictionAndLeakageSpool_2.flange_a, swashPlate_2.flange_a)
          annotation (Line(origin = {-80.0, 80.0}, 
            points = {{20.0, 0.0}, {-20.0, 0.0}}, 
            color = {0, 127, 0}));
        connect(fixedBodyPiston_5.support_b, fixed_Trans.flange)
          annotation (Line(origin = {120.0, -185.0}, 
            points = {{-60.0, 5.0}, {70.0, 5.0}, {70.0, -5.0}}, 
            color = {0, 127, 0}));
        connect(fixedBodyPiston_4.support_b, fixed_Trans.flange)
          annotation (Line(origin = {120.0, -145.0}, 
            points = {{-60.0, 45.0}, {70.0, 45.0}, {70.0, -45.0}}, 
            color = {0, 127, 0}));
        connect(fixed_Trans.flange, fixedBodyPiston_3.support_b)
          annotation (Line(origin = {120.0, -105.0}, 
            points = {{70.0, -85.0}, {70.0, 85.0}, {-60.0, 85.0}}, 
            color = {0, 127, 0}));
        connect(fixed_Trans.flange, fixedBodyPiston_2.support_b)
          annotation (Line(origin = {120.0, -65.0}, 
            points = {{70.0, -125.0}, {70.0, 125.0}, {-60.0, 125.0}}, 
            color = {0, 127, 0}));
        connect(fixed_Trans.flange, fixedBodyPiston_1.support_b)
          annotation (Line(origin = {120.0, -25.0}, 
            points = {{70.0, -165.0}, {70.0, 165.0}, {-60.0, 165.0}}, 
            color = {0, 127, 0}));
        connect(valvePlate_1.port_Out, port_Out)
          annotation (Line(origin = {80.0, 185.0}, 
            points = {{60.0, -15.0}, {70.0, -15.0}, {70.0, 15.0}, {-80.0, 15.0}}, 
            color = {0, 127, 255}));
        connect(valvePlate_2.port_Out, port_Out)
          annotation (Line(origin = {80.0, 145.0}, 
            points = {{60.0, -55.0}, {70.0, -55.0}, {70.0, 55.0}, {-80.0, 55.0}}, 
            color = {0, 127, 255}));
        connect(valvePlate_3.port_Out, port_Out)
          annotation (Line(origin = {75.0, 105.0}, 
            points = {{65.0, -95.0}, {75.0, -95.0}, {75.0, 95.0}, {-75.0, 95.0}}, 
            color = {0, 127, 255}));
        connect(valvePlate_4.port_Out, port_Out)
          annotation (Line(origin = {75.0, 65.0}, 
            points = {{65.0, -135.0}, {75.0, -135.0}, {75.0, 135.0}, {-75.0, 135.0}}, 
            color = {0, 127, 255}));
        connect(valvePlate_5.port_Out, port_Out)
          annotation (Line(origin = {75.0, 25.0}, 
            points = {{65.0, -175.0}, {75.0, -175.0}, {75.0, 175.0}, {-75.0, 175.0}}, 
            color = {0, 127, 255}));
        connect(rotor, swashPlate_3.rotor)
          annotation (Line(origin = {-170.0, 0.0}, 
            points = {{-30.0, 0.0}, {30.0, 0.0}}, 
            color = {0, 0, 0}));
        connect(valvePlate_5.port_In, port_In)
          annotation (Line(origin = {80.0, -185.0}, 
            points = {{60.0, 15.0}, {80.0, 15.0}, {80.0, -15.0}, {-80.0, -15.0}}, 
            color = {0, 127, 255}));
        connect(valvePlate_4.port_In, port_In)
          annotation (Line(origin = {80.0, -145.0}, 
            points = {{60.0, 55.0}, {80.0, 55.0}, {80.0, -55.0}, {-80.0, -55.0}}, 
            color = {0, 127, 255}));
        connect(valvePlate_3.port_In, port_In)
          annotation (Line(origin = {80.0, -105.0}, 
            points = {{60.0, 95.0}, {80.0, 95.0}, {80.0, -95.0}, {-80.0, -95.0}}, 
            color = {0, 127, 255}));
        connect(valvePlate_2.port_In, port_In)
          annotation (Line(origin = {80.0, -65.0}, 
            points = {{60.0, 135.0}, {80.0, 135.0}, {80.0, -135.0}, {-80.0, -135.0}}, 
            color = {0, 127, 255}));
        connect(valvePlate_1.port_In, port_In)
          annotation (Line(origin = {80.0, -25.0}, 
            points = {{60.0, 175.0}, {80.0, 175.0}, {80.0, -175.0}, {-80.0, -175.0}}, 
            color = {0, 127, 255}));
        connect(swashPlate_1.rotor, rotor)
          annotation (Line(origin = {-170.0, 80.0}, 
            points = {{30.0, 80.0}, {-30.0, 80.0}, {-30.0, -80.0}}, 
            color = {0, 0, 0}));
        connect(swashPlate_2.rotor, rotor)
          annotation (Line(origin = {-170.0, 40.0}, 
            points = {{30.0, 40.0}, {-30.0, 40.0}, {-30.0, -40.0}}, 
            color = {0, 0, 0}));
        connect(swashPlate_4.rotor, rotor)
          annotation (Line(origin = {-170.0, -40.0}, 
            points = {{30.0, -40.0}, {-30.0, -40.0}, {-30.0, 40.0}}, 
            color = {0, 0, 0}));
        connect(swashPlate_5.rotor, rotor)
          annotation (Line(origin = {-170.0, -80.0}, 
            points = {{30.0, -80.0}, {-30.0, -80.0}, {-30.0, 80.0}}, 
            color = {0, 0, 0}));
        connect(swashPlate_1.swash_a, swashActuationMech.rotate)
          annotation (Line(origin = {25.0, 105.0}, 
            points = {{-145.0, 75.0}, {-145.0, 95.0}, {145.0, 95.0}, {145.0, -95.0}}, 
            color = {0, 0, 0}));
        connect(swashPlate_2.swash_a, swashActuationMech.rotate)
          annotation (Line(origin = {5.0, 105.0}, 
            points = {{-125.0, -5.0}, {-165.0, -5.0}, {-165.0, 95.0}, {165.0, 95.0}, {165.0, -95.0}}, 
            color = {0, 0, 0}));
        connect(swashPlate_3.swash_a, swashActuationMech.rotate)
          annotation (Line(origin = {5.0, 105.0}, 
            points = {{-125.0, -85.0}, {-165.0, -85.0}, {-165.0, 95.0}, {165.0, 95.0}, {165.0, -95.0}}, 
            color = {0, 0, 0}));
        connect(swashPlate_4.swash_a, swashActuationMech.rotate)
          annotation (Line(origin = {5.0, 70.0}, 
            points = {{-125.0, -130.0}, {-165.0, -130.0}, {-165.0, 130.0}, {165.0, 130.0}, {165.0, -60.0}}, 
            color = {0, 0, 0}));
        connect(swashPlate_5.swash_a, swashActuationMech.rotate)
          annotation (Line(origin = {5.0, 30.0}, 
            points = {{-125.0, -170.0}, {-165.0, -170.0}, {-165.0, 170.0}, {165.0, 170.0}, {165.0, -20.0}}, 
            color = {0, 0, 0}));
      end VariableDisplacementPump0;
      connector Flange_a "平动接口a"
        extends FlangePort;
        annotation (
          defaultComponentName = "flange_a", 
          Documentation(info = "<html>
<p>
This is a connector for 1-dim. rotational mechanical systems and models
the mechanical flange of a shaft. The following variables are defined in this connector:
</p>

<table border=1 cellspacing=0 cellpadding=2>
  <tr><td valign=\"top\"> <b>phi</b></td>
      <td valign=\"top\"> Absolute rotation angle of the shaft flange in [rad] </td>
  </tr>
  <tr><td valign=\"top\"> <b>tau</b></td>
      <td valign=\"top\"> Cut-torque in the shaft flange in [Nm] </td>
  </tr>
</table>

<p>
There is a second connector for flanges: Flange_b. The connectors
Flange_a and Flange_b are completely identical. There is only a difference
in the icons, in order to easier identify a flange variable in a diagram.
For a discussion on the actual direction of the cut-torque tau and
of the rotation angle, see section
<a href=\"modelica://Modelica.Mechanics.Rotational.UsersGuide.SignConventions\">Sign Conventions</a>
in the user's guide of Rotational.
</p>

<p>
If needed, the absolute angular velocity w and the
absolute angular acceleration a of the flange can be determined by
differentiation of the flange angle phi:
</p>
<pre>
     w = der(phi);    a = der(w)
</pre>
</html>"), 
          Icon(coordinateSystem(extent = {{-100.0, -100.0}, {100.0, 100.0}}, 
            grid = {2.0, 2.0}), graphics = {Rectangle(origin = {0.0, 0.0}, 
            lineColor = {0, 127, 0}, 
            fillColor = {0, 127, 0}, 
            fillPattern = FillPattern.Solid, 
            extent = {{-100.0, -100.0}, {100.0, 100.0}})}), 
          Diagram(coordinateSystem(extent = {{-100.0, -100.0}, {100.0, 100.0}}, 
            grid = {2.0, 2.0}), graphics = {Text(origin = {-60.0, 70.0}, 
            extent = {{-100.0, 20.0}, {100.0, -20.0}}, 
            textString = "%name"), Rectangle(origin = {0.0, 0.0}, 
            lineColor = {0, 127, 0}, 
            fillColor = {0, 127, 0}, 
            fillPattern = FillPattern.Solid, 
            extent = {{-40.0, -40.0}, {40.0, 40.0}})}));
      end Flange_a;
      connector FlangePort "基础机械接口"
        SI.Position s "位置";
        flow SI.Force f "力";
      end FlangePort;
      connector OilPort "基础油液接口"
        import SI = Modelica.SIunits;
        flow SI.VolumeFlowRate q(displayUnit = "l") "体积流量";
        SI.AbsolutePressure p(displayUnit = "bar") "压力";
      end OilPort;
      connector OilPort_a "油液接口a"
        extends OilPort;
        annotation (defaultComponentName = "port_A", 
          Diagram(coordinateSystem(extent = {{-100.0, -100.0}, {100.0, 100.0}}, 
            preserveAspectRatio = false, 
            grid = {2.0, 2.0}), graphics = {Ellipse(origin = {0.0, 0.0}, 
            lineColor = {0, 0, 0}, 
            fillColor = {0, 85, 255}, 
            fillPattern = FillPattern.Solid, 
            extent = {{-40.0, 40.0}, {40.0, -40.0}}), Text(origin = {0.0, 80.0}, 
            extent = {{-150.0, 30.0}, {150.0, -30.0}}, 
            textString = "%name")}), 
          Icon(coordinateSystem(extent = {{-100.0, -100.0}, {100.0, 100.0}}, 
            preserveAspectRatio = false, 
            grid = {2.0, 2.0}), graphics = {Ellipse(origin = {0.0, 0.0}, 
            lineColor = {0, 127, 255}, 
            fillColor = {0, 127, 255}, 
            fillPattern = FillPattern.Solid, 
            extent = {{-100.0, 100.0}, {100.0, -100.0}}), Ellipse(origin = {0.0, 0.0}, 
            lineColor = {0, 0, 0}, 
            fillColor = {0, 85, 255}, 
            fillPattern = FillPattern.Solid, 
            extent = {{-100.0, 100.0}, {100.0, -100.0}})}));
      end OilPort_a;
      connector OilPort_b "油液接口b"
        extends OilPort;
        annotation (defaultComponentName = "port_B", 
          Diagram(coordinateSystem(preserveAspectRatio = false, extent = {{-100, 
            -100}, {100, 100}}), graphics = {
            Ellipse(
            extent = {{-40, 40}, {40, -40}}, 
            lineColor = {0, 0, 0}, 
            fillColor = {0, 127, 255}, 
            fillPattern = FillPattern.Solid), 
            Ellipse(
            extent = {{-30, 30}, {30, -30}}, 
            lineColor = {0, 127, 255}, 
            fillColor = {255, 255, 255}, 
            fillPattern = FillPattern.Solid), 
            Text(extent = {{-150, 110}, {150, 50}}, textString = "%name")}), 
          Icon(coordinateSystem(extent = {{-100.0, -100.0}, {100.0, 100.0}}, 
            preserveAspectRatio = false, 
            grid = {2.0, 2.0}), graphics = {Ellipse(origin = {0.0, 0.0}, 
            lineColor = {0, 127, 255}, 
            fillColor = {0, 127, 255}, 
            fillPattern = FillPattern.Solid, 
            extent = {{-100.0, 100.0}, {100.0, -100.0}}), Ellipse(origin = {0.0, 0.0}, 
            lineColor = {0, 0, 0}, 
            fillColor = {0, 85, 255}, 
            fillPattern = FillPattern.Solid, 
            extent = {{-100.0, 100.0}, {100.0, -100.0}}), Ellipse(origin = {0.0, 0.0}, 
            lineColor = {0, 127, 255}, 
            fillColor = {255, 255, 255}, 
            fillPattern = FillPattern.Solid, 
            extent = {{-80.0, 80.0}, {80.0, -80.0}})}));
      end OilPort_b;
      model Tank "油箱"


        parameter SI.Pressure p0 = 0;
        Modelica_Network.YJNY.Pump.Basic.OilPort_b port annotation (Placement(transformation(extent = {{-10.0, -10.0}, {10.0, 10.0}})));


        annotation (Diagram(coordinateSystem(extent = {{-100.0, -100.0}, {100.0, 100.0}}, 
          preserveAspectRatio = false, 
          grid = {2.0, 2.0})), 
          Icon(coordinateSystem(extent = {{-100.0, -100.0}, {100.0, 100.0}}, 
            preserveAspectRatio = false, 
            grid = {2.0, 2.0}), graphics = {Line(origin = {3.552713678800501e-15, -70.0}, 
            points = {{-60.0, 30.0}, {-60.0, -30.0}, {60.0, -30.0}, {60.0, 30.0}}, 
            thickness = 3.0), Line(origin = {0.0, -40.0}, 
            points = {{0.0, -40.0}, {0.0, 40.0}}, 
            color = {0, 85, 255}, 
            thickness = 3.0), Text(origin = {-3.552713678800501e-15, -122.0}, 
            lineColor = {0, 85, 255}, 
            extent = {{-100.0, 20.0}, {100.0, -20.0}}, 
            textString = "%name", 
            fontName = "Times New Roman", 
            textStyle = {TextStyle.None}, 
            textColor = {0, 85, 255}), Line(origin = {-30.0, -7.993605777301127e-15}, 
            points = {{0.0, 18.000000000000007}, {0.0, -17.99999999999995}}, 
            thickness = 2.0, 
            arrow = {Arrow.None, Arrow.Filled}, 
            arrowSize = 10.0)}), Protection(access = Access.packageText));
      equation 
        port.p = p0;
      end Tank;


      model SwashPlate "斜盘"

        // 参数
        parameter SI.Diameter diameter(displayUnit = "mm") = 0.1 "柱塞分布圆直径" 
          annotation (Dialog(group = "结构参数"));
        parameter SI.Angle theta_0(displayUnit = "deg") = 0 "主轴初始转角" 
          annotation (Dialog(group = "初始角"));
        parameter Real fc = 0.1 "摩擦系数" 
          annotation (Dialog(group = "摩擦"));

        // 变量
        SI.Angle phi "斜盘角度";
        SI.AngularVelocity w "斜盘转动角速度";
        SI.Angle theta "主轴旋转角度";
        SI.AngularVelocity w_r "主轴转动角速度";
        SI.Distance s "活塞位移";

        // 接口
        Modelica_Network.YJNY.Pump.Basic.PowerPort_a swash_a
          annotation (
            Placement(transformation(origin = {0.0, 100.0}, 
              extent = {{-10.0, -10.0}, {10.0, 10.0}})));
        Modelica_Network.YJNY.Pump.Basic.PowerPort_b swash_b
          annotation (
            Placement(transformation(origin = {0.0, -100.0}, 
              extent = {{-10.0, -10.0}, {10.0, 10.0}})));
        Modelica_Network.YJNY.Pump.Basic.Flange_a flange_a
          annotation (
            Placement(transformation(origin = {-100.0, 2.0}, 
              extent = {{-10.0, -10.0}, {10.0, 10.0}})));
        Modelica_Network.YJNY.Pump.Basic.PowerPort_a rotor
          annotation (
            Placement(transformation(origin = {100.0, 0.0}, 
              extent = {{-10.0, -10.0}, {10.0, 10.0}})));
        Modelica_Network.YJNY.Pump.Basic.RealOutput angle
          annotation (
            Placement(transformation(origin = {-110.0, -100.0}, 
              extent = {{10.0, -10.0}, {-10.0, 10.0}})));
        annotation (
          Icon(coordinateSystem(extent = {{-100.0, -100.0}, {100.0, 100.0}}, 
            grid = {2.0, 2.0}), graphics = {Polygon(origin = {78.31, 11.35581408961536}, 
            fillColor = {255, 255, 255}, 
            fillPattern = FillPattern.Solid, 
            points = {{-1.1800000000000068, -15.13681408961536}, {-2.8900000000000006, -12.73681408961536}, {-3.75, -11.067014089615359}, {-5.460000000000008, -9.346814089615359}, {-7.390000000000001, -5.926814089615359}, {-8.680000000000007, -3.3568140896153595}, {-8.680000000000007, -0.5658140896153601}, {-7.820000000000007, 3.0741859103846405}, {-7.180000000000007, 6.0741859103846405}, {-5.680000000000007, 9.93418591038464}, {-2.8900000000000006, 12.50418591038464}, {-1.1700000000000017, 13.36418591038464}, {0.09299152205143457, 15.136814089615351}, {2.039999999999992, 12.93418591038464}, {3.75, 10.784185910384641}, {5.039999999999992, 9.07418591038464}, {6.5666040714344405, 6.546785458003038}, {7.649091809999703, 4.150970908728068}, {8.5842909052365, 1.4881540975450775}, {8.679999999999993, -2.5568504262709713}, {7.454290905236519, -6.76654135270665}, {5.307002261908025, -10.944414541996963}, {1.650398190473581, -14.62641589914178}, {-1.1800000000000068, -15.13681408961536}}, 
            smooth = Smooth.Bezier), Rectangle(origin = {-67.99, -0.7214999999999998}, 
            fillColor = {192, 192, 192}, 
            fillPattern = FillPattern.Solid, 
            extent = {{-10.010000000000005, 8.721499999999999}, {10.009999999999998, -8.721499999999999}}), Polygon(origin = {10.545, -9.674999999999997}, 
            lineColor = {40, 40, 40}, 
            fillColor = {192, 192, 192}, 
            fillPattern = FillPattern.Solid, 
            points = {{-10.545, 55.675}, {10.545, 55.985}, {10.545, -55.985}, {-10.545, -55.985}}), Polygon(origin = {-28.8105, 25.2}, 
            fillColor = {192, 192, 192}, 
            fillPattern = FillPattern.Solid, 
            points = {{-25.1895, -9.2}, {-25.1895, 6.29}, {5.0805, 6.29}, {5.0805, 21.2}, {25.1895, 21.2}, {25.1895, -20.748}, {-25.1895, -21.2}}), Polygon(origin = {-28.8105, -26.801000000000002}, 
            fillColor = {192, 192, 192}, 
            fillPattern = FillPattern.Solid, 
            points = {{-25.1895, 9.201}, {-25.1895, -6.289}, {5.0805, -6.289}, {5.0805, -21.199}, {25.1895, -21.199}, {25.1895, 20.747}, {-25.1895, 21.199}}), Ellipse(origin = {-38.865, -0.8650000000000002}, 
            fillColor = {255, 255, 255}, 
            fillPattern = FillPattern.Solid, 
            extent = {{-21.134999999999998, -21.134999999999998}, {21.135, 21.134999999999998}}), Rectangle(origin = {49.72, -3.674999999999999}, 
            fillColor = {255, 255, 255}, 
            fillPattern = FillPattern.Solid, 
            extent = {{-28.619999999999997, 29.725}, {28.620000000000005, -29.725}}), Rectangle(origin = {-30.68, 40.79}, 
            fillColor = {127, 127, 127}, 
            fillPattern = FillPattern.Solid, 
            extent = {{-4.299999999999997, 5.890000000000001}, {4.300000000000001, -5.890000000000001}}), Rectangle(origin = {-30.375, -47.230000000000004}, 
            fillColor = {127, 127, 127}, 
            fillPattern = FillPattern.Solid, 
            extent = {{-3.625, 7.230000000000004}, {3.625, -7.229999999999997}}), Line(origin = {-15.0, -69.83}, 
            points = {{15.0, 4.170000000000002}, {-15.0, -4.170000000000002}}, 
            thickness = 1.0), Ellipse(origin = {-45.0, -79.0}, 
            fillColor = {139, 0, 0}, 
            extent = {{-15.0, -15.0}, {15.0, 15.0}}), Ellipse(origin = {-45.26, -79.1}, 
            fillColor = {127, 127, 127}, 
            fillPattern = FillPattern.Solid, 
            extent = {{-10.0, -10.0}, {10.0, 10.0}}), Line(origin = {-80.0, -90.0}, 
            points = {{-20.0, -10.0}, {20.0, 10.0}}, 
            color = {40, 40, 40}, 
            thickness = 1.0), Line(origin = {-83.95, 0.0}, 
            points = {{-6.05, 0.0}, {6.05, 0.0}}, 
            color = {40, 40, 40}, 
            thickness = 1.0), Polygon(origin = {78.74046494723538, -18.463488724913073}, 
            fillColor = {192, 192, 192}, 
            fillPattern = FillPattern.Solid, 
            points = {{-0.7295790773102908, -15.078496241637684}, {-1.9451565254913135, -14.312943156730395}, {-3.2785071851650116, -12.197718444232326}, {-4.598521360467359, -9.918335425411719}, {-5.888737231930975, -7.4252423300295725}, {-6.926516723003033, -4.097604385895487}, {-7.579999848298101, -0.19017019516137168}, {-7.413654428526996, 3.5916641826042373}, {-6.370518102208649, 7.205030908203138}, {-5.194886181946643, 9.68848573798689}, {-3.6550723260394022, 12.275766912630388}, {-2.200709923443796, 13.890968015994899}, {-0.4200001517017711, 15.078496241637689}, {1.5499833316365823, 13.684198729736519}, {3.367123829256343, 11.748479724976043}, {5.085685819735403, 9.395636739257455}, {6.479983331636575, 6.93249624163769}, {7.229966814974929, 3.826760720215578}, {7.579999848298229, -0.9175037583623116}, {7.1185783554389275, -4.094644255982072}, {5.728561838777296, -7.841751720278555}, {4.8679397145012615, -10.170607090826778}, {3.2953833390666176, -11.924601475782374}, {1.1456613678813596, -14.085333612311874}}, 
            smooth = Smooth.Bezier), Line(origin = {-10.0, 73.2}, 
            points = {{10.0, 26.799999999999997}, {-10.0, -26.799999999999997}}, 
            color = {40, 40, 40}, 
            thickness = 1.0), Line(origin = {-10.0, -74.0}, 
            points = {{10.0, -26.0}, {-10.0, 26.0}}, 
            color = {40, 40, 40}, 
            thickness = 1.0)}));
      equation 
        // 斜盘旋转接口方程
        swash_a.phi = phi;
        swash_b.phi = phi;
        w = der(phi);
        flange_a.f * cos(phi) * diameter / 2 * cos(theta) = swash_a.tau + swash_b.tau;

        // 主轴旋转接口方程
        rotor.phi + theta_0 = theta;
        w_r = der(theta);
        fc * flange_a.f * cos(phi) * diameter / 2 = rotor.tau;

        // 柱塞运动接口方程
        flange_a.s = s;

        // 活塞运动方程
        s = tan(phi) * diameter / 2 * (1 - cos(theta));

        // 弧度转角度方程
        angle = theta / pi * 180;
      end SwashPlate;
      connector RealInput = input Real "实型输入接口" 
        annotation (
          defaultComponentName = "u", 
          Icon(graphics = {
            Polygon(
            lineColor = {0, 0, 127}, 
            fillColor = {0, 0, 127}, 
            fillPattern = FillPattern.Solid, 
            points = {{-100.0, 100.0}, {100.0, 0.0}, {-100.0, -100.0}})}, 
            coordinateSystem(extent = {{-100.0, -100.0}, {100.0, 100.0}}, 
              preserveAspectRatio = true, 
              initialScale = 0.2)), 
          Diagram(
            coordinateSystem(preserveAspectRatio = true, 
              initialScale = 0.2, 
              extent = {{-100.0, -100.0}, {100.0, 100.0}}), 
            graphics = {
            Polygon(
            lineColor = {0, 0, 127}, 
            fillColor = {0, 0, 127}, 
            fillPattern = FillPattern.Solid, 
            points = {{0.0, 50.0}, {100.0, 0.0}, {0.0, -50.0}, {0.0, 50.0}}), 
            Text(
            lineColor = {0, 0, 127}, 
            extent = {{-10.0, 60.0}, {-10.0, 85.0}}, 
            textString = "%name")}), 
          Documentation(info = "<html>
<p>
单输入的实型接口.
</p>
</html>"));
      connector RealOutput = output Real "实型输出接口" 
        annotation (
          defaultComponentName = "y", 
          Icon(
            coordinateSystem(preserveAspectRatio = true, 
              extent = {{-100.0, -100.0}, {100.0, 100.0}}, 
              initialScale = 0.1), 
            graphics = {
            Polygon(
            lineColor = {0, 0, 127}, 
            fillColor = {255, 255, 255}, 
            fillPattern = FillPattern.Solid, 
            points = {{-100.0, 100.0}, {100.0, 0.0}, {-100.0, -100.0}})}), 
          Diagram(
            coordinateSystem(preserveAspectRatio = true, 
              extent = {{-100.0, -100.0}, {100.0, 100.0}}, 
              initialScale = 0.1), 
            graphics = {
            Polygon(
            lineColor = {0, 0, 127}, 
            fillColor = {255, 255, 255}, 
            fillPattern = FillPattern.Solid, 
            points = {{-100.0, 50.0}, {0.0, 0.0}, {-100.0, -50.0}}), 
            Text(
            lineColor = {0, 0, 127}, 
            extent = {{30.0, 60.0}, {30.0, 110.0}}, 
            textString = "%name")}), 
          Documentation(info = "<html>
<p>
单输出实型接口.
</p>
</html>"));
      model ViscousFrictionAndLeakagePiston "考虑粘性摩擦和泄漏的活塞模型"
        outer Media.OilMedia.Oil oil;

        // 结构参数
        parameter SI.Length ds(displayUnit = "mm") = 1E-2 "筒径" 
          annotation (Dialog(group = "结构参数"));
        parameter SI.Length len0(displayUnit = "mm") = 0 "阀芯初始偏移量" 
          annotation (Dialog(group = "结构参数"));
        parameter SI.Length dc(displayUnit = "mm") = 5e-6 "径向间隙 (0<dc<<ds)" 
          annotation (Dialog(group = "结构参数"));
        final parameter SI.Length rc = dc / 2;

        parameter Real e = 0 "活塞的离心率" 
          annotation (Dialog(group = "泄漏"));

        // 方法配置
        parameter Boolean reverse = false
          "true：反向放置 ‖ false：正向放置，默认为fasle" annotation (Dialog(group = "方法配置"));
        final parameter Integer switch = if reverse then -1 else 1 annotation (Evaluate = true);

        final parameter SI.Length L_cmin(displayUnit = "mm") = 10 * dc "最小接触长度" 
          annotation (Dialog(tab = "接触"));

        // 变量
        SI.Pressure pA "A口压力";
        SI.Pressure pB "B口压力";
        SI.Pressure dp "进出口压差";
        SI.VolumeFlowRate q "流量";

        SI.Distance x "阀芯位置";
        SI.Velocity vel "阀芯运动速度";
        SI.Length L_con "等效接触长度";

        SI.Force f "柱塞与套筒之间的摩擦力";

        // 接口
        Modelica_Network.YJNY.Pump.Basic.OilPort_a port_A "油液进口" 
          annotation (Placement(transformation(origin = {50, -100}, 
            extent = {{-9.0, -9.0}, {8.999999999999993, 9.000000000000043}})));
        Modelica_Network.YJNY.Pump.Basic.OilPort_b port_B "油液出口" 
          annotation (Placement(transformation(origin = {-50, -100}, 
            extent = {{-9.0, -9.0}, {9.0, 9.0}})));
        Modelica_Network.YJNY.Pump.Basic.Flange_a support_a
          annotation (
            Placement(transformation(origin = {-100.0, -100.0}, 
              extent = {{-10.0, -10.0}, {10.0, 10.0}})));
        Modelica_Network.YJNY.Pump.Basic.Flange_a support_b
          annotation (
            Placement(transformation(origin = {100.0, -100.0}, 
              extent = {{-10.0, -10.0}, {10.0, 10.0}})));
        Modelica_Network.YJNY.Pump.Basic.Flange_a flange_b
          annotation (
            Placement(transformation(origin = {100.0, 0.0}, 
              extent = {{-10.0, -10.0}, {10.0, 10.0}})));
        Modelica_Network.YJNY.Pump.Basic.Flange_a flange_a
          annotation (
            Placement(transformation(origin = {-100.0, 0.0}, 
              extent = {{-10.0, -10.0}, {10.0, 10.0}})));
        annotation (Diagram(coordinateSystem(extent = {{-100.0, -100.0}, {100.0, 100.0}}, 
          grid = {2.0, 2.0})), 
          Icon(coordinateSystem(extent = {{-100.0, -100.0}, {100.0, 100.0}}, 
            grid = {2.0, 2.0}), graphics = {Rectangle(origin = {0.0, 0.0}, 
            fillColor = {255, 255, 255}, 
            fillPattern = FillPattern.Solid, 
            lineThickness = 1.0, 
            extent = {{-90.0, 2.0}, {90.0, -2.0}}), Rectangle(origin = {0.0, 85.0}, 
            fillColor = {175, 175, 175}, 
            fillPattern = FillPattern.Solid, 
            lineThickness = 1.0, 
            extent = {{-100.0, 15.0}, {100.0, -15.0}}), Rectangle(origin = {0.0, -85.0}, 
            fillColor = {175, 175, 175}, 
            fillPattern = FillPattern.Solid, 
            lineThickness = 1.0, 
            extent = {{-40.0, 15.0}, {40.0, -15.0}}), Text(origin = {-3.552713678800501e-15, 119.99999999999999}, 
            lineColor = {0, 128, 0}, 
            extent = {{-67.0, 20.0}, {67.0, -19.999999999999986}}, 
            textString = "%name", 
            textStyle = {TextStyle.None}, 
            textColor = {0, 128, 0}, 
            horizontalAlignment = LinePattern.None), Line(origin = {0.0, -64.0}, 
            points = {{-32.0, 0.0}, {-32.0, 0.0}, {32.0, 0.0}, {28.0, 0.0}}, 
            color = {0, 85, 255}, 
            thickness = 2.0), Polygon(origin = {2.0, 1.0}, 
            fillColor = {175, 175, 175}, 
            fillPattern = FillPattern.Solid, 
            lineThickness = 1.0, 
            points = {{-40.0, 61.0}, {40.0, 61.0}, {40.0, 45.0}, {6.0, 45.0}, {6.0, -45.0}, {38.0, -45.0}, {38.0, -61.0}, {-40.0, -61.0}, {-40.0, -45.0}, {-10.0, -45.0}, {-10.0, 45.0}, {-40.0, 45.0}}), Line(origin = {2.0, 66.0}, 
            points = {{-30.0, 0.0}, {30.0, 0.0}}, 
            color = {0, 85, 255}, 
            thickness = 2.0), Rectangle(origin = {-80.0, -85.0}, 
            fillColor = {175, 175, 175}, 
            fillPattern = FillPattern.Solid, 
            lineThickness = 1.0, 
            extent = {{-20.0, 15.000000000000014}, {20.0, -15.0}}), Rectangle(origin = {79.99999999999997, -85.0}, 
            fillColor = {175, 175, 175}, 
            fillPattern = FillPattern.Solid, 
            lineThickness = 1.0, 
            extent = {{-20.0, 15.000000000000014}, {20.0, -15.0}})}));
      equation 
        // 接口方程
        support_a.s = support_b.s;
        flange_a.s = flange_b.s;
        flange_a.f + flange_b.f + switch * f = 0;
        support_a.f + support_b.f = 0;

        pA = port_A.p;
        pB = port_B.p;
        dp = pA - pB;

        q = port_A.q;
        port_A.q + port_B.q = 0;

        // 阀芯位置
        assert(rc > 0, "径向间隙必须≥0");
        x = len0 + switch * (flange_a.s - support_a.s);
        vel = der(x);

        // 接触长度
        L_con = noEvent(if x >= L_cmin then x else L_cmin);

        // 流量方程
        q = dp / (12 * oil.mu * L_con) * rc ^ 3 * pi * ds * (1 + 3 / 2 * (e / rc) ^ 2);

        // 阀芯受力
        f = pi * oil.mu * (ds - dc) * L_con / rc * vel;
      end ViscousFrictionAndLeakagePiston;
      model FixedBodyPiston "带固定端的活塞"

        //参数
        parameter SI.Diameter ds(displayUnit = "mm") = 0.01 "筒径" 
          annotation (Dialog(group = "结构参数"));
        parameter SI.Length len0(displayUnit = "mm") = 0 "初始压力腔长度" 
          annotation (Dialog(group = "结构参数"));
        parameter SI.Volume v0(displayUnit = "ml") = 0 "死区容积" 
          annotation (Dialog(group = "结构参数"));

        parameter SI.Mass m = 1 "活塞质量";

        parameter Boolean reverse = false
          "true：反向放置 ‖ false：正向放置，默认为fasle" annotation (Dialog(group = "方法配置"));

        final parameter Integer n_ports = 1 "端口数量" 
          annotation (Dialog(connectorSizing = true));
        final parameter SI.Volume V0(displayUnit = "ml") = 1e-5 "初始容积";

        // 组件
        FixedBodyPiston0 fixedBodyPiston01(
          ds = ds, final dr = 0, len0 = len0, v0 = v0, 
          reverse = reverse)
          annotation (Placement(transformation(origin = {40.0, 0.0}, 
            extent = {{-20.0, -20.0}, {20.0, 20.0}})));
        Modelica.Mechanics.Translational.Components.Mass mass(m = m)
          annotation (Placement(transformation(origin = {-50.0, 0.0}, 
            extent = {{-20.0, -20.0}, {20.0, 20.0}})));

        // 接口
        Modelica_Network.YJNY.Pump.Basic.Flange_a flange_a
          annotation (Placement(transformation(origin = {-100.0, 0.0}, 
            extent = {{-10.0, -10.0}, {10.0, 10.0}})));
        Modelica_Network.YJNY.Pump.Basic.Flange_a flange_b
          annotation (Placement(transformation(origin = {100.0, 0.0}, 
            extent = {{-10.0, -10.0}, {10.0, 10.0}})));
        Modelica_Network.YJNY.Pump.Basic.Flange_a support_b
          annotation (Placement(transformation(origin = {100.0, -100.0}, 
            extent = {{-10.0, -10.0}, {10.0, 10.0}})));
        Modelica_Network.YJNY.Pump.Basic.Flange_a support_a
          annotation (Placement(transformation(origin = {-100.0, -100.0}, 
            extent = {{-10.0, -10.0}, {10.0, 10.0}})));
        Modelica_Network.YJNY.Pump.Basic.OilPort_a port_a "油液接口" 
          annotation (Placement(transformation(origin = {50.0, -100.0}, 
            extent = {{-10.0, -10.0}, {10.0, 10.0}})));

        /******************************监测量******************************/
        SI.Force f = m * der(v) "活塞受合力";
        SI.Force f_p = fixedBodyPiston01.f "活塞受油液压力";
        SI.Position x = fixedBodyPiston01.x "阀芯相对壁面位移";
        SI.Velocity v = fixedBodyPiston01.v "阀芯相对壁面速度";

        SI.Pressure p = fixedBodyPiston01.p "油压";
        SI.VolumeFlowRate q = fixedBodyPiston01.q "体积流量";
        annotation (Diagram(coordinateSystem(extent = {{-100.0, -100.0}, {100.0, 100.0}}, 
          grid = {2.0, 2.0})), 
          Icon(coordinateSystem(extent = {{-100.0, -100.0}, {100.0, 100.0}}, 
            grid = {2.0, 2.0}), graphics = {Rectangle(origin = {0.0, 0.0}, 
            fillColor = {255, 255, 255}, 
            fillPattern = FillPattern.Solid, 
            lineThickness = 1.0, 
            extent = {{-90.0, 2.0}, {90.0, -2.0}}), Rectangle(origin = {-48.0, -1.000000000000007}, 
            fillColor = {177, 177, 177}, 
            fillPattern = FillPattern.Solid, 
            lineThickness = 1.0, 
            extent = {{-2.0, 71.0}, {2.0, -71.0}}), Rectangle(origin = {-60.0, -2.1316282072803006e-14}, 
            fillColor = {229, 229, 229}, 
            fillPattern = FillPattern.Solid, 
            lineThickness = 1.0, 
            extent = {{-10.0, 75.0}, {10.0, -74.99999999999999}}), Rectangle(origin = {-30.0, -86.00000000000003}, 
            fillColor = {175, 175, 175}, 
            fillPattern = FillPattern.Solid, 
            lineThickness = 1.0, 
            extent = {{-70.0, 15.0}, {70.0, -15.0}}), Line(origin = {7.0, 40.0}, 
            points = {{43.0, 0.0}, {-43.0, 0.0}}, 
            color = {0, 0, 0}, 
            thickness = 1.0, 
            arrow = {Arrow.None, Arrow.Filled}, 
            arrowSize = 6.0), Line(origin = {7.0, -40.0}, 
            points = {{43.0, 0.0}, {-43.0, 0.0}}, 
            color = {0, 0, 0}, 
            thickness = 1.0, 
            arrow = {Arrow.None, Arrow.Filled}, 
            arrowSize = 6.0), Text(origin = {-3.552713678800501e-15, 119.99999999999999}, 
            lineColor = {0, 128, 0}, 
            extent = {{-67.0, 20.0}, {67.0, -19.999999999999986}}, 
            textString = "%name", 
            textStyle = {TextStyle.None}, 
            textColor = {0, 128, 0}, 
            horizontalAlignment = LinePattern.None), Polygon(origin = {80.0, -52.0}, 
            fillColor = {175, 175, 175}, 
            fillPattern = FillPattern.Solid, 
            lineThickness = 1.0, 
            points = {{-20.0, -18.0}, {-20.0, -48.0}, {20.0, -48.0}, {20.0, 48.0}, {-10.0, 48.0}, {-10.0, -18.0}}), Polygon(origin = {0.0, 52.0}, 
            fillColor = {175, 175, 175}, 
            fillPattern = FillPattern.Solid, 
            lineThickness = 1.0, 
            points = {{-100.0, 48.0}, {100.0, 48.0}, {100.0, -48.0}, {70.0, -48.0}, {70.0, 18.0}, {-100.0, 18.0}})}));
      equation 
        connect(fixedBodyPiston01.flange_b, flange_b)
          annotation (Line(origin = {60.0, 0.0}, 
            points = {{0.0, 0.0}, {40.0, 0.0}}, 
            color = {0, 127, 0}));
        connect(fixedBodyPiston01.support_b, support_b)
          annotation (Line(origin = {60.0, -60.0}, 
            points = {{0.0, 40.0}, {40.0, 40.0}, {40.0, -40.0}}, 
            color = {0, 127, 0}));
        connect(fixedBodyPiston01.support_a, support_a)
          annotation (Line(origin = {-60.0, -60.0}, 
            points = {{80.0, 40.0}, {60.0, 40.0}, {60.0, -40.0}, {-40.0, -40.0}}, 
            color = {0, 127, 0}));
        connect(fixedBodyPiston01.flange_a, mass.flange_b)
          annotation (Line(origin = {-32.0, 0.0}, 
            points = {{52.0, 0.0}, {2.0, 0.0}}, 
            color = {0, 127, 0}));
        connect(mass.flange_a, flange_a)
          annotation (Line(origin = {-82.0, 0.0}, 
            points = {{12.0, 0.0}, {-18.0, 0.0}}, 
            color = {0, 127, 0}));
        connect(fixedBodyPiston01.port_a, port_a)
          annotation (Line(origin = {50.0, -60.0}, 
            points = {{0.0, 40.0}, {0.0, -40.0}}, 
            color = {0, 127, 255}));
      end FixedBodyPiston;

      model FixedBodyPiston0 "带固定端的活塞"

        //参数
        parameter SI.Diameter ds(displayUnit = "mm") = 0.01 "筒径" 
          annotation (Dialog(group = "结构参数"));
        parameter SI.Diameter dr(displayUnit = "mm") = 0.005 "杆径" 
          annotation (Dialog(group = "结构参数"));
        parameter SI.Length len0(displayUnit = "mm") = 0 "初始压力腔长度" 
          annotation (Dialog(group = "结构参数"));
        parameter SI.Volume v0(displayUnit = "ml") = 0 "死区容积" 
          annotation (Dialog(group = "结构参数"));
        final parameter SI.Area area = (ds ^ 2 - dr ^ 2) * pi / 4 "通流面积";

        parameter Boolean reverse = false
          "true：反向放置 ‖ false：正向放置，默认为fasle" annotation (Dialog(group = "方法配置"));
        final parameter Integer switch = if reverse then -1 else 1 annotation (Evaluate = true);

        //变量
        SI.Force f "作用在flange_a正方向的力";
        SI.Length length "腔体长度";
        SI.Position x "阀芯相对壁面位移";
        SI.Velocity v "阀芯相对壁面速度";

        SI.Pressure p "油压";
        SI.VolumeFlowRate q "体积流量";

        // 接口
        Modelica_Network.YJNY.Pump.Basic.Flange_a support_a
          annotation (
            Placement(transformation(origin = {-100.0, -100.0}, 
              extent = {{-10.0, -10.0}, {10.0, 10.0}})));
        Modelica_Network.YJNY.Pump.Basic.Flange_a support_b
          annotation (
            Placement(transformation(origin = {100.0, -100.0}, 
              extent = {{-10.0, -10.0}, {10.0, 10.0}})));
        Modelica_Network.YJNY.Pump.Basic.Flange_a flange_b
          annotation (
            Placement(transformation(origin = {100.0, 0.0}, 
              extent = {{-10.0, -10.0}, {10.0, 10.0}})));
        Modelica_Network.YJNY.Pump.Basic.Flange_a flange_a
          annotation (
            Placement(transformation(origin = {-100.0, 0.0}, 
              extent = {{-10.0, -10.0}, {10.0, 10.0}})));
        Modelica_Network.YJNY.Pump.Basic.OilPort_a port_a "油液接口" 
          annotation (Placement(transformation(origin = {50.0, -100.0}, 
            extent = {{-10.0, -10.0}, {10.0, 10.0}})));
        annotation (Protection(access = Access.packageText));
        annotation (Diagram(coordinateSystem(extent = {{-100.0, -100.0}, {100.0, 100.0}}, 
          grid = {2.0, 2.0})), 
          Icon(coordinateSystem(extent = {{-100.0, -100.0}, {100.0, 100.0}}, 
            grid = {2.0, 2.0}), graphics = {Rectangle(origin = {0.0, 0.0}, 
            fillColor = {255, 255, 255}, 
            fillPattern = FillPattern.Solid, 
            lineThickness = 1.0, 
            extent = {{-90.0, 2.0}, {90.0, -2.0}}), Rectangle(origin = {-48.0, -1.000000000000007}, 
            fillColor = {177, 177, 177}, 
            fillPattern = FillPattern.Solid, 
            lineThickness = 1.0, 
            extent = {{-2.0, 71.0}, {2.0, -71.0}}), Rectangle(origin = {-60.0, -2.1316282072803006e-14}, 
            fillColor = {229, 229, 229}, 
            fillPattern = FillPattern.Solid, 
            lineThickness = 1.0, 
            extent = {{-10.0, 75.0}, {10.0, -74.99999999999999}}), Rectangle(origin = {-30.0, -86.00000000000003}, 
            fillColor = {175, 175, 175}, 
            fillPattern = FillPattern.Solid, 
            lineThickness = 1.0, 
            extent = {{-70.0, 15.0}, {70.0, -15.0}}), Line(origin = {7.0, 40.0}, 
            points = {{43.0, 0.0}, {-43.0, 0.0}}, 
            color = {0, 0, 0}, 
            thickness = 1.0, 
            arrow = {Arrow.None, Arrow.Filled}, 
            arrowSize = 6.0), Line(origin = {7.0, -40.0}, 
            points = {{43.0, 0.0}, {-43.0, 0.0}}, 
            color = {0, 0, 0}, 
            thickness = 1.0, 
            arrow = {Arrow.None, Arrow.Filled}, 
            arrowSize = 6.0), Text(origin = {-3.552713678800501e-15, 119.99999999999999}, 
            lineColor = {0, 128, 0}, 
            extent = {{-67.0, 20.0}, {67.0, -19.999999999999986}}, 
            textString = "%name", 
            textStyle = {TextStyle.None}, 
            textColor = {0, 128, 0}, 
            horizontalAlignment = LinePattern.None), Polygon(origin = {80.0, -52.0}, 
            fillColor = {175, 175, 175}, 
            fillPattern = FillPattern.Solid, 
            lineThickness = 1.0, 
            points = {{-20.0, -18.0}, {-20.0, -48.0}, {20.0, -48.0}, {20.0, 48.0}, {-10.0, 48.0}, {-10.0, -18.0}}), Polygon(origin = {0.0, 52.0}, 
            fillColor = {175, 175, 175}, 
            fillPattern = FillPattern.Solid, 
            lineThickness = 1.0, 
            points = {{-100.0, 48.0}, {100.0, 48.0}, {100.0, -48.0}, {70.0, -48.0}, {70.0, 18.0}, {-100.0, 18.0}})}));
      equation 
        // 接口方程
        flange_a.s = flange_b.s;
        support_b.s = support_a.s;

        flange_a.f + flange_b.f + switch * f = 0;
        support_a.f + support_b.f = 0;

        p = port_a.p;
        q = -port_a.q;

        // 活塞位移
        x = switch * (flange_a.s - support_a.s) + len0;
        length = noEvent(max(x, 0));
        der(length) = v;

        // 流量方程
        assert(ds > dr, "ds必须大于dr", level = AssertionLevel.error);
        q = v * area;

        // 力平衡
        f = p * area;
      end FixedBodyPiston0;
      model CompressVolume "可压缩油液体积"
        outer Media.OilMedia.Oil oil;

        parameter SI.Volume V(displayUnit = "l") = 1e-6 "控制体积";
        parameter SI.Pressure pstart = 1e5;

        SI.Pressure p(start = pstart) "油压";
        SI.Pressure beta = oil.B "液体体积模量";

        Modelica_Network.YJNY.Pump.Basic.OilPort_a port_A
          annotation (Placement(transformation(origin = {0, -100.0}, 
            extent = {{-10.0, -10.0}, {10.0, 10.0}})));
      equation 
        p = port_A.p;
        V * der(p) = beta * port_A.q;
        annotation (Icon(coordinateSystem(extent = {{-100.0, -100.0}, {100.0, 100.0}}, 
          grid = {2.0, 2.0}), graphics = {Ellipse(origin = {0.0, 0.0}, 
          fillColor = {0, 85, 255}, 
          fillPattern = FillPattern.Solid, 
          extent = {{-50.0, -50.0}, {50.0, 50.0}}), Line(origin = {0.0, -75.0}, 
          points = {{0.0, -25.0}, {0.0, 25.0}})}));
      end CompressVolume;
      model ValvePlate "配流盘"
        outer Media.OilMedia.Oil oil;

        // 参数
        parameter SI.Diameter diameter_orifice(displayUnit = "mm") = 0.011 "最大节流孔直径";
        parameter Real signal_max(unit = "1", min = 1) = 1 "最大输入信号";
        parameter Real Cqmax(unit = "1") = 0.707 "配油盘节流孔流量系数";
        parameter Real cycle = 360 "循环差值周期" 
          annotation (Dialog(group = "自定义通流面积"));
        parameter Real[:,:] table_data = [0, 0; 10, 0.5; 15, 1; 175, 1; 180, 0; 360, 0] "配油盘节流孔吸油窗与排油窗分布" 
          annotation (Dialog(group = "自定义通流面积"));
        parameter Modelica.Blocks.Types.Smoothness smoothness = Modelica.Blocks.Types.Smoothness.LinearSegments "插值方式" 
          annotation (Dialog(group = "自定义通流面积"));

        final parameter SI.Area area_max(min = 0.0) = pi / 4 * diameter_orifice ^ 2 "配油盘节流孔最大面积";

        // 压力
        SI.AbsolutePressure p_in "进油口压力";
        SI.AbsolutePressure p_out "出油口压力";
        SI.AbsolutePressure p_chamber "柱塞腔压力";
        SI.Pressure dp_in "端口压差";
        SI.Pressure dp_out "端口压差";

        // 流量
        SI.VolumeFlowRate q_chamber(displayUnit = "l/min") "柱塞腔流量";
        SI.VolumeFlowRate q_in(displayUnit = "l/min") "入口流量";
        SI.VolumeFlowRate q_out(displayUnit = "l/min") "出口流量";
        // SI.Density rho_in "进油口密度";
        // SI.Density rho_out "出油口密度";
        SI.Density rho "油液密度";

        // 流通面积
        //SI.BulkModulus beta "体积模量";
        Real commandedArea_in "通流面积信号";
        Real commandedArea_out "通流面积信号";
        SI.Area area_in "进油口通流面积";
        SI.Area area_out "出油口通流面积";

        // 组件
        Modelica.Blocks.Tables.CombiTable1Ds flowareain(
          table = table_data, 
          smoothness = smoothness)
          annotation (Placement(transformation(origin = {18.0, -88.0}, 
            extent = {{10.0, -10.0}, {-10.0, 10.0}})));
        Modelica.Blocks.Tables.CombiTable1Ds flowareaout(
          table = table_data, 
          smoothness = smoothness)
          annotation (Placement(transformation(origin = {50.0, -88.0}, 
            extent = {{10.0, -10.0}, {-10.0, 10.0}})));

        // 接口
        Modelica_Network.YJNY.Pump.Basic.OilPort_b port_Out "出口" 
          annotation (Placement(transformation(origin = {-100.0, 50.0}, 
            extent = {{-10.0, -10.0}, {10.0, 10.0}})));
        Modelica_Network.YJNY.Pump.Basic.OilPort_a port_In "入口" 
          annotation (Placement(transformation(origin = {-100.0, -50.0}, 
            extent = {{-10.0, -10.0}, {10.0, 10.0}})));
        Modelica_Network.YJNY.Pump.Basic.OilPort_a port_chamber "活塞腔接口" 
          annotation (Placement(transformation(origin = {100.0, 50.0}, 
            extent = {{-10.0, -10.0}, {10.0, 10.0}})));
        Modelica_Network.YJNY.Pump.Basic.RealInput rotorangle
          annotation (Placement(transformation(origin = {110.0, -100.0}, 
            extent = {{10.0, -10.0}, {-10.0, 10.0}})));

        annotation (Protection(access = Access.packageText));
        annotation (
          Icon(coordinateSystem(extent = {{-100.0, -100.0}, {100.0, 100.0}}, 
            grid = {2.0, 2.0}), graphics = {Polygon(origin = {-29.0, 81.00000000000001}, 
            fillColor = {192, 192, 192}, 
            fillPattern = FillPattern.Solid, 
            points = {{-39.0, 16.999999999999986}, {39.0, 16.999999999999986}, {39.0, -17.000000000000014}, {-19.0, -17.000000000000014}, {-39.0, -5.000000000000014}}), Polygon(origin = {40.626000000000005, 80.86}, 
            fillColor = {192, 192, 192}, 
            fillPattern = FillPattern.Solid, 
            points = {{-24.626, 17.14}, {24.714, 17.14}, {24.714, -1.22}, {5.444, -1.22}, {-9.636, -17.14}, {-24.714, -17.14}}), Polygon(origin = {-29.0, -2.9999999999999982}, 
            fillColor = {192, 192, 192}, 
            fillPattern = FillPattern.Solid, 
            points = {{-39.0, -33.0}, {-39.0, 23.0}, {-19.0, 33.0}, {39.0, 33.0}, {39.0, -15.000000000000002}, {27.0, -15.000000000000002}, {27.0, -33.0}}), Polygon(origin = {40.954499999999996, 3.0}, 
            fillColor = {192, 192, 192}, 
            fillPattern = FillPattern.Solid, 
            points = {{-25.0455, 27.0}, {-9.9645, 27.0}, {5.1255, 11.0}, {25.0455, 11.0}, {25.0455, -27.0}, {-12.8345, -26.84}, {-12.8345, 2.672}, {-24.9965, 2.672}}), Ellipse(origin = {9.0, -51.0}, 
            fillColor = {255, 255, 255}, 
            fillPattern = FillPattern.Solid, 
            lineThickness = 0.5, 
            extent = {{-9.0, -15.0}, {9.0, 15.0}}), Rectangle(origin = {-28.930000000000007, -66.96000000000001}, 
            fillColor = {255, 255, 255}, 
            fillPattern = FillPattern.Solid, 
            lineThickness = 0.5, 
            extent = {{-39.07, 30.960000000000008}, {39.07, -30.959999999999994}}), Ellipse(origin = {9.0, -82.0}, 
            fillColor = {192, 192, 192}, 
            fillPattern = FillPattern.Solid, 
            lineThickness = 0.5, 
            extent = {{-9.0, -16.0}, {9.0, 16.0}}), Line(origin = {-2.0, 40.0}, 
            points = {{0.0, 58.0}, {0.0, -58.0}}, 
            thickness = 0.5), Line(origin = {10.0, 47.0}, 
            points = {{0.0, 17.0}, {0.0, -17.0}}), Line(origin = {15.911999999999999, 46.86}, 
            points = {{0.0, 16.86}, {0.0, -16.86}}), Line(origin = {30.99, 46.86}, 
            points = {{0.0, 16.86}, {0.0, -16.86}}, 
            thickness = 0.5), Line(origin = {46.08, 46.82}, 
            points = {{0.0, 32.82}, {0.0, -32.82}}, 
            thickness = 0.5), Line(origin = {-1.0, 48.0}, 
            points = {{-69.0, 0.0}, {69.0, 0.0}}, 
            pattern = LinePattern.DashDotDot, 
            thickness = 0.5), Line(origin = {-2.0, -66.0}, 
            points = {{-72.0, 0.0}, {72.0, 0.0}}, 
            pattern = LinePattern.DashDotDot, 
            thickness = 0.5), Line(origin = {60.00000000000001, -62.0}, 
            points = {{5.999999999999993, 4.0}, {-6.000000000000007, -4.0}}, 
            thickness = 0.5), Line(origin = {60.0, -70.0}, 
            points = {{-6.0, 4.0}, {6.0, -4.0}}, 
            thickness = 0.5), Line(origin = {75.0, -66.0}, 
            points = {{-5.0, 0.0}, {5.0, 0.0}}, 
            thickness = 0.5), Text(origin = {12.0, 120.0}, 
            lineColor = {0, 128, 0}, 
            extent = {{0.0, 20.0}, {0.0, -20.0}}, 
            textString = "%name", 
            textStyle = {TextStyle.None}, 
            textColor = {0, 128, 0}), Line(origin = {90.0, -83.0}, 
            points = {{10.0, -17.0}, {-10.0, -17.0}, {-10.0, 17.0}}, 
            thickness = 0.5)}));
      equation 
        // 接口方程
        p_in = port_In.p;
        p_out = port_Out.p;
        p_chamber = port_chamber.p;
        dp_in = p_in - p_chamber;
        // dp_out = p_chamber - p_out;
        dp_out = p_out - p_chamber;

        q_in = port_In.q;
        q_out = port_Out.q;
        q_chamber = port_chamber.q;

        // 通流面积
        mod(rotorangle, cycle) = flowareaout.u;
        commandedArea_out = flowareaout.y[1];
        mod(rotorangle + 180, cycle) = flowareain.u;
        commandedArea_in = flowareain.y[1];
        area_in = noEvent(if commandedArea_in <= 0 then 0 else if commandedArea_in <= signal_max then 
          commandedArea_in / signal_max * area_max else area_max);
        area_out = noEvent(if commandedArea_out <= 0 then 0 else if commandedArea_out <= signal_max then 
          commandedArea_out / signal_max * area_max else area_max);

        // 流量方程
        rho = oil.rho;
        q_in = area_in * Cqmax * regRoot2(dp_in, 5, 2 / rho, 2 / rho);
        q_out = area_out * Cqmax * regRoot2(dp_out, 5, 2 / rho, 2 / rho);
        q_chamber + q_in + q_out = 0;
      end ValvePlate;
      model SwashActuationMech2 "斜盘变量机构"

        // 参数
        parameter SI.Diameter Lactuator(displayUnit = "mm") = 0.06 "变量机构与斜盘转轴间距";
        parameter SI.Angle phimax = 0.349065850398866 "最大斜盘倾角";
        final parameter SI.Length smax = Lactuator * tan(phimax);

        // 变量
        SI.Angle phi "斜盘倾角";

        // 接口
        Modelica_Network.YJNY.Pump.Basic.PowerPort_a rotate
          annotation (Placement(transformation(origin = {0.0, -100.0}, 
            extent = {{-10.0, -10.0}, {10.0, 10.0}})));
        Modelica_Network.YJNY.Pump.Basic.Flange_a flange
          annotation (Placement(transformation(origin = {-100.0, 0.0}, 
            extent = {{-10.0, -10.0}, {10.0, 10.0}})));
        annotation (Protection(access = Access.packageText));
        annotation (
          Icon(coordinateSystem(extent = {{-100.0, -100.0}, {100.0, 100.0}}, 
            grid = {2.0, 2.0}), graphics = {Rectangle(origin = {-50.0, 0.0}, 
            fillColor = {255, 255, 255}, 
            fillPattern = FillPattern.Solid, 
            extent = {{-40.0, 8.0}, {40.0, -8.0}}), Polygon(origin = {23.0, 0.0}, 
            fillColor = {192, 192, 192}, 
            fillPattern = FillPattern.Solid, 
            points = {{-23.0, 17.0}, {-23.0, 32.0}, {3.0, 32.0}, {3.0, 46.0}, {23.0, 46.0}, {23.0, -46.0}, {5.0, -46.0}, {5.0, -30.0}, {-23.0, -30.0}}), Ellipse(origin = {0.0, 1.7763568394002505e-15}, 
            fillColor = {255, 255, 255}, 
            fillPattern = FillPattern.Solid, 
            extent = {{-20.0, -20.0}, {20.0, 20.0}}), Polygon(origin = {38.0, 0.0}, 
            fillColor = {127, 127, 127}, 
            fillPattern = FillPattern.Solid, 
            points = {{-18.0, 40.0}, {-18.0, 60.0}, {18.0, 60.0}, {18.0, -60.0}, {-18.0, -60.0}, {-18.0, -40.0}, {-12.0, -40.0}, {-12.0, -54.0}, {10.0, -54.0}, {10.0, 54.0}, {-14.0, 54.0}, {-14.0, 40.0}}), Line(origin = {27.99999999999426, -91.0}, 
            points = {{28.00000000000574, 9.0}, {-27.99999999999426, -9.0}}, 
            thickness = 1.0), Line(origin = {56.0, -71.0}, 
            points = {{0.0, 11.0}, {0.0, -11.0}}, 
            thickness = 1.0)}));
      equation 
        //斜盘倾角
        phi = rotate.phi;
        tan(phi) = (smax - flange.s) / Lactuator;
        // assert(smax - flange.s >= 0, "变量机构移动长度超限");

        //斜盘力矩平衡方程
        flange.f * cos(phi) * Lactuator + rotate.tau = 0;
      end SwashActuationMech2;
      model Fixed "Fixed flange"
        parameter SI.Position s0 = 0 "Fixed offset position of housing";

        Modelica_Network.YJNY.Pump.Basic.Flange_a flange annotation (Placement(transformation(
          extent = {{-10, 10}, {10, -10}}, 
          rotation = 180)));
      equation 
        flange.s = s0;
        annotation (Icon(coordinateSystem(extent = {{-100.0, -100.0}, {100.0, 100.0}}, 
          grid = {2.0, 2.0}), graphics = {Line(origin = {0.0, -40.0}, 
          points = {{-80.0, 0.0}, {80.0, 0.0}}, 
          color = {0, 127, 0}), Line(origin = {60.0, -60.0}, 
          points = {{20.0, 20.0}, {-20.0, -20.0}}, 
          color = {0, 127, 0}), Line(origin = {20.0, -60.0}, 
          points = {{20.0, 20.0}, {-20.0, -20.0}}, 
          color = {0, 127, 0}), Line(origin = {-20.0, -60.0}, 
          points = {{20.0, 20.0}, {-20.0, -20.0}}, 
          color = {0, 127, 0}), Line(origin = {-60.0, -60.0}, 
          points = {{20.0, 20.0}, {-20.0, -20.0}}, 
          color = {0, 127, 0}), Line(origin = {0.0, -25.0}, 
          points = {{0.0, -15.0}, {0.0, 15.0}}, 
          color = {0, 127, 0}), Text(origin = {0.0, -100.0}, 
          lineColor = {0, 128, 0}, 
          extent = {{-100.0, 20.0}, {100.0, -20.0}}, 
          textString = "%name", 
          textStyle = {TextStyle.None}, 
          textColor = {0, 128, 0})}), 
          Documentation(info = "<html>
<p>
The <em>flange</em> of a 1D translational mechanical system <em>fixed</em>
at an position s0 in the <em>housing</em>. May be used:
</p>
<ul>
<li> to connect a compliant element, such as a spring or a damper,
     between a sliding mass and the housing.</li>
<li> to fix a rigid element, such as a sliding mass, at a specific
     position.</li>
</ul>

</html>"));
      end Fixed;
      model PositionSensor "理想位移传感器"
        extends Modelica_Network.YJNY.Pump.Basic.PartialAbsoluteSensor;
        Modelica.Blocks.Interfaces.RealOutput s(unit = "m") "绝对位移" 
          annotation (Placement(
            transformation(extent = {{100, -11}, {120, 9}}), 
            iconTransformation(extent = {{100, -10}, {120, 10}})));
      equation 
        s = flange.s;
        annotation (
          Icon(coordinateSystem(preserveAspectRatio = true, 
            extent = {{-100, -100}, 
            {100, 100}}), graphics = {
            Text(
            extent = {{80, -28}, {114, -62}}, 
            textString = "s")}));
      end PositionSensor;
      partial model PartialAbsoluteSensor "位移传感器基类"
        Modelica_Network.YJNY.Pump.Basic.Flange_a flange
          annotation (Placement(transformation(extent = {{-110, -10}, {-90, 10}})));
        annotation (
          Icon(coordinateSystem(preserveAspectRatio = false, extent = {{-100, -100}, {100, 100}}), graphics = {
            Rectangle(
            fillColor = {245, 245, 245}, 
            fillPattern = FillPattern.Solid, 
            extent = {{-70.0, -60.0}, {70.0, 20.0}}), 
            Polygon(
            pattern = LinePattern.None, 
            fillPattern = FillPattern.Solid, 
            points = {{0.0, -40.0}, {-10.0, -16.0}, {10.0, -16.0}, {0.0, -40.0}}), 
            Line(points = {{0.0, 0.0}, {0.0, -16.0}}), 
            Line(points = {{-70.0, 0.0}, {0.0, 0.0}}), 
            Line(points = {{-50.0, -40.0}, {-50.0, -60.0}}), 
            Line(points = {{-30.0, -40.0}, {-30.0, -60.0}}), 
            Line(points = {{-10.0, -40.0}, {-10.0, -60.0}}), 
            Line(points = {{10.0, -40.0}, {10.0, -60.0}}), 
            Line(points = {{30.0, -40.0}, {30.0, -60.0}}), 
            Line(points = {{50.0, -40.0}, {50.0, -60.0}})}), 
          Documentation(info = "<html>
<p>
This icon is designed for a <strong>translational sensor</strong> model.
</p></html>"));
      equation 
        0 = flange.f;
        annotation (Documentation(info = "<html>
<p>
This is the superclass of a 1D translational component with one flange and one
output signal in order to measure an absolute kinematic quantity in the flange
and to provide the measured signal as output signal for further processing
with the Modelica.Blocks blocks.
</p>
</html>"), Icon(coordinateSystem(preserveAspectRatio = true, extent = {{-100, -100}, 
          {100, 100}}), graphics = {Line(points = {{-100, -90}, {-20, -90}}, color = {95, 127, 95}), 
          Polygon(
          points = {{10, -90}, {-20, -80}, {-20, -100}, {10, -90}}, 
          lineColor = {95, 127, 95}, 
          fillColor = {95, 127, 95}, 
          fillPattern = FillPattern.Solid), Line(points = {{-70, 0}, {-100, 0}}, color = {0, 127, 0}), 
          Line(points = {{70, 0}, {100, 0}}, color = {0, 0, 127}), 
          Text(
          extent = {{-150, 80}, {150, 40}}, 
          textString = "%name", 
          lineColor = {0, 0, 255})}));
      end PartialAbsoluteSensor;

      model SingleActingCylinder "单连杆液压缸"
        // 参数
        parameter SI.Diameter D(displayUnit = "mm") = 0.05 "活塞直径" 
          annotation (Dialog(group = "基本参数"));
        parameter SI.Diameter d(displayUnit = "mm") = 0.024 "活塞杆直径" 
          annotation (Dialog(group = "基本参数"));
        parameter SI.Mass m = 0.5 "活塞质量" 
          annotation (Dialog(group = "基本参数"));
        parameter SI.Length L(displayUnit = "mm") = 0.1 "活塞行程" 
          annotation (Dialog(group = "基本参数"));
        parameter Boolean UseG = false "true，考虑泄漏||false，不考虑泄漏" 
          annotation (Dialog(group = "基本参数"));
        parameter Conductance G(displayUnit = "l/(min.bar)") = 4.2e-13 "泄漏率" 
          annotation (Dialog(group = "基本参数", enable = UseG));
        parameter SI.TranslationalDampingConstant c = 2000 "活塞运动阻尼" 
          annotation (Dialog(group = "基本参数"));
        parameter SI.Volume deadVolume = 2e-5 "死区容积" 
          annotation (Dialog(tab = "限位参数"));
        parameter SI.TranslationalSpringConstant c_c = 1e8 "接触刚度" 
          annotation (Dialog(tab = "限位参数"));
        parameter SI.TranslationalDampingConstant d_c = 1e6 "接触阻尼" 
          annotation (Dialog(tab = "限位参数"));
        parameter SI.Distance initialPosition(displayUnit = "mm") = 0 "活塞初始位置" 
          annotation (Dialog(group = "初始参数"));

        // 体积参数
        parameter Boolean UseVolumeA = true "若为true，则使用容积A" 
          annotation (Dialog(tab = "容积"));
        parameter Boolean UseVolumeB = true "若为true，则使用容积B" 
          annotation (Dialog(tab = "容积"));
        parameter SI.Volume Volume_A(displayUnit = "ml") = 1e-6 "容积A的体积" 
          annotation (Dialog(tab = "容积", enable = UseVolumeA));
        parameter SI.Volume Volume_B(displayUnit = "ml") = 1e-6 "容积B的体积" 
          annotation (Dialog(tab = "容积", enable = UseVolumeB));

        // 高级
        parameter StateSelect stateSelect = StateSelect.default
          "使用速度作为状态变量" annotation (Dialog(tab = "高级"));

        // 组件
        Modelica_Network.YJNY.Pump.Basic.CompressVolume volume_A(V = Volume_A) if UseVolumeA
          "端口A容积" 
          annotation (Placement(transformation(origin = {-80.0, -20.0}, 
            extent = {{-10.0, -10.0}, {10.0, 10.0}})));
        Modelica_Network.YJNY.Pump.Basic.CompressVolume volume_B(V = Volume_B) if UseVolumeB
          "端口B容积" 
          annotation (Placement(transformation(origin = {80.0, -20.0}, 
            extent = {{10.0, -10.0}, {-10.0, 10.0}})));
        SingleActingCylinder1 actingCylinder(
          D = D, 
          d = d, 
          m = m, 
          L = L, 
          c = c, 
          c_c = c_c, 
          d_c = d_c, 
          initialPosition = initialPosition, 
          stateSelect = stateSelect)
          "液压缸" 
          annotation (Placement(transformation(origin = {-0, 0.0}, 
            extent = {{-40.0, -40.0}, {40.0, 40.0}})));
        Filter resistance(G = G) if UseG
          "泄漏流阻" 
          annotation (Placement(transformation(origin = {0.0, -60}, 
            extent = {{-10.0, -10.0}, {10.0, 10.0}}, 
            rotation = 90.0)));

        // 接口
        OilPort_a port_A "流入组件端口" 
          annotation (Placement(transformation(origin = {-50.0, -60.0}, 
            extent = {{-10.0, -10.0}, {10.0, 10.0}})));
        OilPort_b port_B "流出组件端口" 
          annotation (Placement(transformation(origin = {50.0, -60.0}, 
            extent = {{-10.0, -10.0}, {10.0, 10.0}})));
        Flange_a support
          annotation (Placement(transformation(origin = {-100.0, 50.0}, 
            extent = {{-10.0, -10.0}, {10.0, 10.0}})));
        Flange_a flange_b
          annotation (Placement(transformation(origin = {100.0, 0.0}, 
            extent = {{-10.0, -10.0}, {10.0, 10.0}})));

        /**********************************监测量**********************************/
        SI.Length s = actingCylinder.s "活塞位移，无杆腔至有杆腔移动为正";
        SI.Force f = actingCylinder.f "接触力";
        SI.Force fA = actingCylinder.fA "无杆腔液压受力";
        SI.Force fB = actingCylinder.fB "有杆腔液压受力";
        SI.Force ff = actingCylinder.ff "活塞运动阻尼力";
        SI.Pressure pA = actingCylinder.pA "无杆腔油压";
        SI.Pressure pB = actingCylinder.pB "有杆腔油压";
        SI.VolumeFlowRate qA(displayUnit = "l/min") = actingCylinder.qA "无杆腔流量";
        SI.VolumeFlowRate qB(displayUnit = "l/min") = actingCylinder.qB "有杆腔流量";
        annotation (
          Diagram(coordinateSystem(extent = {{-100.0, -100.0}, {100.0, 100.0}}, 
            grid = {2.0, 2.0})), 
          Icon(coordinateSystem(extent = {{-100.0, -100.0}, {100.0, 100.0}}, 
            grid = {2.0, 2.0}), graphics = {Rectangle(visible = UseVolumeA, 
            origin = {-50.0, -60.0}, 
            lineColor = {0, 0, 255}, 
            fillColor = {255, 255, 255}, 
            lineThickness = 0.5, 
            extent = {{-14.0, 14.0}, {14.0, -14.0}}), Rectangle(visible = UseVolumeB, 
            origin = {50.0, -60.0}, 
            lineColor = {0, 0, 255}, 
            fillColor = {255, 255, 255}, 
            lineThickness = 0.5, 
            extent = {{-14.0, 14.0}, {14.0, -14.0}}), Rectangle(origin = {0.0, 0.0}, 
            fillColor = {255, 255, 255}, 
            fillPattern = FillPattern.Solid, 
            lineThickness = 1.0, 
            extent = {{-76.0, -36.0}, {76.0, 36.0}}), Line(origin = {-1.0, 0.0}, 
            points = {{0.0, -30.0}, {0.0, 30.0}}, 
            thickness = 3.0), Line(origin = {45.0, 0.0}, 
            points = {{-43.0, 0.0}, {43.0, 0.0}}, 
            thickness = 2.0), Line(origin = {-50.0, -45.0}, 
            points = {{0.0, 7.0}, {0.0, -5.0}}, 
            color = {0, 85, 255}, 
            thickness = 3.0), Line(origin = {50.0, -46.0}, 
            points = {{0.0, 8.0}, {0.0, -8.0}}, 
            color = {0, 85, 255}, 
            thickness = 3.0), Rectangle(origin = {0.0, 0.0}, 
            fillColor = {255, 255, 255}, 
            fillPattern = FillPattern.Solid, 
            lineThickness = 1.0, 
            extent = {{-76.0, -36.0}, {76.0, 36.0}}), Line(origin = {-1.0, 0.0}, 
            points = {{0.0, -30.0}, {0.0, 30.0}}, 
            thickness = 3.0), Line(origin = {45.0, 0.0}, 
            points = {{-43.0, 0.0}, {43.0, 0.0}}, 
            thickness = 2.0), Line(origin = {50.0, -46.0}, 
            points = {{0.0, 8.0}, {0.0, -8.0}}, 
            color = {0, 85, 255}, 
            thickness = 3.0), Text(origin = {0.0, 80.0}, 
            lineColor = {0, 128, 0}, 
            extent = {{-100.0, 20.0}, {100.0, -20.0}}, 
            textString = "%name", 
            fontName = "Times New Roman", 
            textStyle = {TextStyle.None}, 
            textColor = {0, 128, 0})}));
      equation 
        connect(flange_b, actingCylinder.flange_b)
          annotation (Line(origin = {-77.0, 0.0}, 
            points = {{177.0, 0.0}, {117.0, 0.0}}, 
            color = {0, 127, 0}));
        connect(port_A, actingCylinder.port_A)
          annotation (Line(origin = {-48.0, -48.0}, 
            points = {{-2.0, -12.0}, {-2.0, 8.0}, {28.0, 8.0}, {28.0, 24.0}}, 
            color = {0, 0, 255}));
        connect(support, actingCylinder.support)
          annotation (Line(origin = {-74.0, 37.0}, 
            points = {{-26.0, 13.0}, {34.0, 13.0}, {34.0, -17.0}}, 
            color = {0, 127, 0}));
        connect(actingCylinder.port_A, resistance.port_a)
          annotation (Line(origin = {-18.0, -38.0}, 
            points = {{-2.0, 14.0}, {-2.0, -22.0}, {8.0, -22.0}}, 
            color = {0, 127, 255}));
        connect(actingCylinder.port_B, resistance.port_b)
          annotation (Line(origin = {17.0, -38.0}, 
            points = {{3.0, 14.0}, {3.0, -22.0}, {-7.0, -22.0}}, 
            color = {0, 127, 255}));
        connect(volume_A.port_A, actingCylinder.port_A)
          annotation (Line(origin = {-50.0, -32.0}, 
            points = {{-30.0, 2.0}, {-30.0, -8.0}, {30.0, -8.0}, {30.0, 8.0}}, 
            color = {0, 127, 255}));
        connect(actingCylinder.port_B, port_B)
          annotation (Line(origin = {35.0, -42.0}, 
            points = {{-15.0, 18.0}, {-15.0, 2.0}, {15.0, 2.0}, {15.0, -18.0}}, 
            color = {0, 127, 255}));
        connect(actingCylinder.port_B, volume_B.port_A)
          annotation (Line(origin = {50.0, -32.0}, 
            points = {{-30.0, 8.0}, {-30.0, -8.0}, {30.0, -8.0}, {30.0, 2.0}}, 
            color = {0, 127, 255}));
      end SingleActingCylinder;
      model Filter "过滤器"
        parameter Conductance G = 0.57e-9 "通流率";
        SI.Pressure dp "两端口压差";
        SI.VolumeFlowRate q(displayUnit = "l/min", start = 0.002) "通流流量";
        OilPort_a port_a "进口" 
          annotation (Placement(transformation(origin = {0.0, 100.0}, 
            extent = {{-10.0, -10.0}, {10.0, 10.0}})));
        OilPort_b port_b "出口" 
          annotation (Placement(transformation(origin = {0.0, -100}, 
            extent = {{-10.0, -10.0}, {10.0, 10.0}})));
        annotation (Icon(coordinateSystem(extent = {{-100.0, -100.0}, {100.0, 100.0}}, 
          grid = {2.0, 2.0}), graphics = {Line(origin = {0.0, 78.0}, 
          points = {{0.0, 12.0}, {0.0, -12.0}}, 
          color = {0, 85, 255}, 
          thickness = 3.0), Line(origin = {0.0, -78.0}, 
          points = {{0.0, 14.0}, {0.0, -14.0}}, 
          color = {0, 85, 255}, 
          thickness = 3.0), Rectangle(origin = {0.0, 0.0}, 
          rotation = 45.0, 
          lineColor = {0, 0, 0}, 
          fillColor = {255, 255, 255}, 
          lineThickness = 3.0, 
          extent = {{-45.0, 45.0}, {45.0, -45.0}}), Line(origin = {0.0, 0.0}, 
          points = {{-55.0, 0.0}, {55.0, 0.0}}, 
          color = {0, 0, 0}, 
          pattern = LinePattern.Dash, 
          thickness = 3.0)}));
      equation 
        // 端口流量压力方程
        port_a.q + port_b.q = 0;
        port_a.q = q;
        dp = port_a.p - port_b.p;
        q = G * dp;
      end Filter;
      type Conductance = Real(
        final quantity = "Conductance", 
        final min = 0.0, 
        final unit = "m3/(s.Pa)", 
        displayUnit = "m3/(s.Pa)") "液导单位m3/(s.Pa)";
      model SingleActingCylinder1 "单连杆液压缸"
        // BA反向无流量
        outer Media.OilMedia.Oil oil;

        // 结构参数
        parameter SI.Diameter D = 0.05 "活塞直径" 
          annotation (Dialog(group = "结构参数"));
        parameter SI.Diameter d = 0.024 "活塞杆直径" 
          annotation (Dialog(group = "结构参数"));
        parameter SI.Mass m = 0.5 "活塞质量" 
          annotation (Dialog(group = "结构参数"));
        parameter SI.Length L = 0.5 "活塞行程" 
          annotation (Dialog(group = "结构参数"));
        parameter SI.TranslationalDampingConstant c = 2000 "活塞运动阻尼" 
          annotation (Dialog(group = "结构参数"));

        // 限位参数
        parameter SI.Volume deadVolume = 1e-5 "死区容积" 
          annotation (Dialog(tab = "限位参数"));
        parameter SI.TranslationalSpringConstant c_c = 1e8 "接触刚度" 
          annotation (Dialog(tab = "限位参数"));
        parameter SI.TranslationalDampingConstant d_c = 1e6 "接触阻尼" 
          annotation (Dialog(tab = "限位参数"));

        // 初始参数
        parameter SI.Distance initialPosition = 0 "活塞初始位置" 
          annotation (Dialog(group = "初始参数"));
        final parameter SI.Area activeArea_L(
          min = 0.0) = 1 / 4 * pi * D ^ 2 "压力有效作用面积";
        final parameter SI.Area activeArea_R(
          min = 0.0) = 1 / 4 * pi * (D ^ 2 - d ^ 2) "压力有效作用面积";

        // 高级
        parameter StateSelect stateSelect = StateSelect.default
          "使用速度作为状态变量" annotation (Dialog(tab = "高级"));

        // 变量
        SI.Length s(start = initialPosition, fixed = true) "活塞位移，无杆腔至有杆腔移动为正";
        SI.Velocity v(start = 0, stateSelect = stateSelect) "活塞运动速度";
        SI.Acceleration a "活塞运动加速度";
        SI.Volume effVolume_L "无杆腔等效体积";
        SI.Volume effVolume_R "有杆腔等效体积";
        SI.VolumeFlowRate chamberFlow_L "由于活塞运动引起的无杆腔流量";
        SI.VolumeFlowRate chamberFlow_R "由于活塞运动引起的有杆腔流量";
        SI.BulkModulus beta "体积模量";

        // 受力
        SI.Force f "接触力";
        SI.Force f_c "等效接触弹簧力";
        SI.Force f_d2 "等效接触阻尼力中间变量";
        SI.Force f_d "等效接触阻尼力";
        SI.Force fA "无杆腔液压受力";
        SI.Force fB "有杆腔液压受力";
        SI.Force ff "活塞运动阻尼力";

        // 接口参数
        SI.Pressure pA "无杆腔油压";
        SI.Pressure pB "有杆腔油压";
        SI.VolumeFlowRate qA(displayUnit = "l/min") "无杆腔流量";
        SI.VolumeFlowRate qB(displayUnit = "l/min") "有杆腔流量";

        // 接口
        OilPort_a port_A
          annotation (Placement(transformation(origin = {-50.0, -60}, 
            extent = {{-10.0, -10.0}, {10.0, 10.0}})));
        OilPort_b port_B
          annotation (Placement(transformation(origin = {50.0, -60.0}, 
            extent = {{-10.0, -10.0}, {10.0, 10.0}})));
        Flange_a support
          annotation (Placement(transformation(origin = {-100.0, 50.0}, 
            extent = {{-10.0, -10.0}, {10.0, 10.0}})));
        Flange_a flange_b
          annotation (Placement(transformation(origin = {100.0, 0.0}, 
            extent = {{-10.0, -10.0}, {10.0, 10.0}})));

        annotation (
          Diagram(coordinateSystem(extent = {{-100.0, -100.0}, {100.0, 100.0}}, 
            grid = {2.0, 2.0})), 
          Icon(coordinateSystem(extent = {{-100.0, -100.0}, {100.0, 100.0}}, 
            grid = {2.0, 2.0}), graphics = {Rectangle(origin = {0.0, 0.0}, 
            fillColor = {255, 255, 255}, 
            fillPattern = FillPattern.Solid, 
            lineThickness = 1.0, 
            extent = {{-76.0, -36.0}, {76.0, 36.0}}), Line(origin = {-1.0, 0.0}, 
            points = {{0.0, -30.0}, {0.0, 30.0}}, 
            thickness = 3.0), Line(origin = {45.0, 0.0}, 
            points = {{-43.0, 0.0}, {43.0, 0.0}}, 
            thickness = 2.0), Line(origin = {-50.0, -45.0}, 
            points = {{0.0, 7.0}, {0.0, -5.0}}, 
            color = {0, 85, 255}, 
            thickness = 3.0), Line(origin = {50.0, -46.0}, 
            points = {{0.0, 8.0}, {0.0, -8.0}}, 
            color = {0, 85, 255}, 
            thickness = 3.0), Text(origin = {0.0, 80.0}, 
            lineColor = {0, 128, 0}, 
            extent = {{-100.0, 20.0}, {100.0, -20.0}}, 
            textString = "%name", 
            fontName = "Times New Roman", 
            textStyle = {TextStyle.None}, 
            textColor = {0, 128, 0})}));
      equation 
        // 接口方程
        pA = port_A.p;
        pB = port_B.p;

        qA = port_A.q;
        qB = port_B.q;

        s = flange_b.s - support.s;
        0 = flange_b.f + support.f;

        // 速度、加速度方程
        v = der(s);
        a = der(v);

        // 等效容积
        effVolume_L = activeArea_L * s + deadVolume;
        effVolume_R = activeArea_R * (L - s) + deadVolume;

        // 流量
        chamberFlow_L = -v * activeArea_L;
        chamberFlow_R = -v * activeArea_R;

        // 压力
        der(pA) = beta / effVolume_L * (qA + chamberFlow_L);
        der(pB) = beta / effVolume_R * (qB - chamberFlow_R);
        beta = oil.B;

        // 活塞受力方程
        m * a = fA - fB - ff - flange_b.f + f;
        fA = pA * activeArea_L;
        fB = pB * activeArea_R;
        ff = c * v;

        // 接触力计算
        f_c = smooth(1, noEvent(if s < 0 then c_c * abs(s) else if s > L then -c_c * abs(s - L) else 0));
        f_d2 = noEvent(if s < 0 then -d_c * v else if s > L then -d_c * v else 0);
        f_d = smooth(0, noEvent(if s < 0 then (if f_d2 > f_c then f_c else if 
          f_d2 < -f_c then -f_c else f_d2) else if s > L then (if f_d2 < f_c then f_c else if 
          f_d2 > -f_c then -f_c else f_d2) else 0));
        f = f_c + f_d;

      end SingleActingCylinder1;
      model ThreeWaysValve "三通阀"

        // 结构参数
        parameter SI.Mass m = 0.1 "阀芯质量" 
          annotation (Dialog(group = "结构参数"));
        parameter SI.Length smax(displayUnit = "mm") = 0.1 "阀芯最大行程" 
          annotation (Dialog(group = "结构参数"));
        parameter SI.Length ds(displayUnit = "mm") = 0.01 "筒径" 
          annotation (Dialog(group = "结构参数"));
        parameter SI.Length dr(displayUnit = "mm") = 0.005 "杆径" 
          annotation (Dialog(group = "结构参数"));
        parameter SI.Diameter Dh(displayUnit = "mm") = 0.005 "节流孔直径" 
          annotation (Dialog(group = "结构参数"));

        // 弹簧参数
        parameter SI.Length s_0(displayUnit = "mm") = 0.01 "弹簧初始长度" 
          annotation (Dialog(group = "弹簧参数"));
        parameter SI.TranslationalSpringConstant k = 1000 "弹簧刚度" 
          annotation (Dialog(group = "弹簧参数"));
        final parameter SI.Force fspr0 = k * s_0 "弹簧预紧力";

        // 死区容积
        parameter SI.Volume v0(displayUnit = "ml") = 0 "死区容积" 
          annotation (Dialog(group = "高级"));

        // 高级
        parameter Boolean UseJetForce = false "若为true，则考虑液动力，否则不考虑液动力" 
          annotation (Dialog(tab = "高级", group = "液动力"));
        parameter SI.Angle theta = 1.20427718387609 "阀口射流角" 
          annotation (Dialog(tab = "高级", group = "液动力", enable = UseJetForce));
        parameter Real Cqmax = 0.707 "最大流量系数" 
          annotation (Dialog(tab = "高级", group = "流体参数"));
        parameter Real lambda_crit = 1000 "临界流量数" 
          annotation (Dialog(tab = "高级", group = "流体参数"));

        parameter Real F_prop(unit = "N.s/m", final min = 0) = 100 "阀芯滑动摩擦系数" 
          annotation (Dialog(tab = "高级", group = "阀芯摩擦"));
        parameter SI.Force F_Coulomb = 0 "库仑摩擦力" 
          annotation (Dialog(tab = "高级", group = "阀芯摩擦"));
        parameter SI.Force F_Stribeck = 0 "Stribeck效应" 
          annotation (Dialog(tab = "高级", group = "阀芯摩擦"));
        parameter Real fexp(unit = "s/m", final min = 0) = 0 "衰减指数" 
          annotation (Dialog(tab = "高级", group = "阀芯摩擦"));

        // 组件
        Modelica.Mechanics.Translational.Components.MassWithStopAndFriction mass(
          smax = smax, 
          smin = -smax, 
          F_Coulomb = F_Coulomb, 
          F_prop = F_prop, 
          F_Stribeck = F_Stribeck, 
          fexp = fexp, 
          L = 0, 
          s(start = 0), 
          m = m)
          "阀芯质量" 
          annotation (Placement(transformation(extent = {{-10.0, -10.0}, {10.0, 10.0}})));
        AnnularOrificeSpool spool_P(
          ds = ds, 
          dr = dr, 
          Dh = Dh, 
          v0 = v0, 
          theta = theta, 
          UseJetForce = UseJetForce, 
          Cqmax = Cqmax, 
          lambda_crit = lambda_crit)
          "P腔阀芯" 
          annotation (Placement(transformation(origin = {-40.0, 0}, 
            extent = {{10.0, -10.0}, {-10.0, 10.0}})));
        FixedBodyPiston0 piston_P(
          ds = ds, 
          dr = dr, 
          v0 = v0)
          "P腔活塞" 
          annotation (Placement(transformation(origin = {-80.0, 0}, 
            extent = {{10.0, -10.0}, {-10.0, 10.0}})));
        SpringPiston piston_T(
          reverse = true, 
          ds = ds, 
          dr = ds, 
          v0 = v0, 
          s_0 = s_0, 
          k = k)
          "T腔活塞" 
          annotation (Placement(transformation(origin = {80.0, 0.0}, 
            extent = {{-10.0, -10.0}, {10.0, 10.0}})));
        AnnularOrificeSpool spool_T(
          reverse = true, 
          ds = ds, 
          dr = dr, 
          Dh = Dh, 
          v0 = v0, 
          theta = theta, 
          UseJetForce = UseJetForce, 
          Cqmax = Cqmax, 
          lambda_crit = lambda_crit)
          "T腔阀芯" 
          annotation (Placement(transformation(origin = {40.0, 0.0}, 
            extent = {{-10.0, -10.0}, {10.0, 10.0}})));
        Fixed fixed_P
          annotation (Placement(transformation(origin = {-100.0, -20.0}, 
            extent = {{-10.0, -10.0}, {10.0, 10.0}})));
        Fixed fixed_T
          annotation (Placement(transformation(origin = {100.0, -20.0}, 
            extent = {{-10.0, -10.0}, {10.0, 10.0}})));
        Tank tank_P
          annotation (Placement(transformation(origin = {-85.0, -50.0}, 
            extent = {{-10.0, -10.0}, {10.0, 10.0}})));
        Tank tank_T
          annotation (Placement(transformation(origin = {85.0, -50.0}, 
            extent = {{-10.0, -10.0}, {10.0, 10.0}})));

        // 接口
        OilPort_b port_A "A口" 
          annotation (Placement(transformation(origin = {0.0, -50.0}, 
            extent = {{-10.0, -10.0}, {10.0, 10.0}})));
        OilPort_a port_P "P口" 
          annotation (Placement(transformation(origin = {-50.0, 50.0}, 
            extent = {{-10.0, -10.0}, {10.0, 10.0}})));
        OilPort_b port_T "T口" 
          annotation (Placement(transformation(origin = {50.0, 50.0}, 
            extent = {{-10.0, -10.0}, {10.0, 10.0}})));

        /***********************************监测量***********************************/
        SI.Position x = mass.s "阀芯相对壁面位移，向P腔移动为正";
        SI.Force fP = spool_P.f "P腔阀芯受力";
        SI.Force fT = spool_T.f "T腔阀芯受力";
        SI.Force fjet_P = spool_P.fjet "P腔液动力";
        SI.Force fjet_T = spool_T.fjet "T腔液动力";
        SI.Force fspr = piston_T.fspr "T腔弹簧力";

        SI.Area area_orifice_P = spool_P.area_orifice "P腔阀孔通流面积";
        SI.Area area_orifice_T = spool_T.area_orifice "T腔阀孔通流面积";

        SI.Pressure pA = port_A.p "A口油压";
        SI.Pressure pP = port_P.p "P口油压";
        SI.Pressure pT = port_T.p "T口油压";
        SI.Pressure dp_PA = pP - pA "PA口压差";
        SI.Pressure dp_PT = pT - pA "TA口油压";
        SI.VolumeFlowRate qA(displayUnit = "l/min") = port_A.q "A口流量";
        SI.VolumeFlowRate qP(displayUnit = "l/min") = port_P.q "P口流量";
        SI.VolumeFlowRate qT(displayUnit = "l/min") = port_T.q "T口流量";

        annotation (Icon(coordinateSystem(extent = {{-100.0, -100.0}, {100.0, 100.0}}, 
          grid = {2.0, 2.0}), graphics = {Text(origin = {0.0, 80.0}, 
          lineColor = {0, 85, 255}, 
          extent = {{-100.0, 20.0}, {100.0, -20.0}}, 
          textString = "%name", 
          fontName = "Times New Roman", 
          textStyle = {TextStyle.None}, 
          textColor = {0, 85, 255}), Rectangle(origin = {0.0, 0.0}, 
          fillColor = {255, 255, 255}, 
          fillPattern = FillPattern.Solid, 
          lineThickness = 1.0, 
          extent = {{-60.0, 30.0}, {60.0, -30.0}}), Line(origin = {0.0, 0.0}, 
          points = {{0.0, 30.0}, {0.0, -30.0}}, 
          thickness = 1.0), Line(origin = {-10.0, 30.0}, 
          points = {{0.0, 0.0}, {0.0, -10.0}}, 
          thickness = 1.0), Line(origin = {-10.0, 20.0}, 
          points = {{-5.0, 0.0}, {5.0, 0.0}}, 
          thickness = 1.0), Line(origin = {10.0, 30.0}, 
          points = {{0.0, 0.0}, {0.0, -10.0}}, 
          thickness = 1.0), Line(origin = {10.0, 20.0}, 
          points = {{-5.0, 0.0}, {5.0, 0.0}}, 
          thickness = 1.0), Line(origin = {0.0, 35.0}, 
          points = {{-60.0, 0.0}, {60.0, 0.0}}, 
          thickness = 0.5), Line(origin = {0.0, -35.0}, 
          points = {{-60.0, 0.0}, {60.0, 0.0}}, 
          thickness = 0.5), Line(origin = {-50.0, 0.0}, 
          points = {{0.0, 28.0}, {0.0, -28.0}}, 
          thickness = 1.0, 
          arrow = {Arrow.None, Arrow.Filled}, 
          arrowSize = 7.0), Line(origin = {30.0, 0.0}, 
          points = {{-20.0, -28.0}, {20.0, 28.0}}, 
          thickness = 1.0, 
          arrow = {Arrow.None, Arrow.Filled}, 
          arrowSize = 7.0), Line(origin = {-50.0, 37.5}, 
          points = {{0.0, -2.5}, {0.0, 2.5}}, 
          color = {0, 85, 255}, 
          thickness = 1.0), Line(origin = {50.0, 37.5}, 
          points = {{0.0, -2.5}, {0.0, 2.5}}, 
          color = {0, 85, 255}, 
          thickness = 1.0), Line(origin = {0.0, -37.499999999999986}, 
          points = {{0.0, -2.5}, {0.0, 2.5}}, 
          color = {0, 85, 255}, 
          thickness = 1.0), Line(origin = {80.0, 3.0}, 
          points = {{-20.0, -3.0}, {-12.0, 11.0}, {-6.0, -17.0}, {4.0, 17.0}, {8.0, -17.0}, {14.0, 5.0}, {20.0, -3.0}}, 
          thickness = 0.5), Text(origin = {-70.0, 50.0}, 
          lineColor = {0, 85, 255}, 
          extent = {{-10.0, 10.0}, {10.0, -10.0}}, 
          textString = "P", 
          textStyle = {TextStyle.None}, 
          textColor = {0, 85, 255}), Text(origin = {70.0, 50.0}, 
          lineColor = {0, 85, 255}, 
          extent = {{-10.0, 10.0}, {10.0, -10.0}}, 
          textString = "T", 
          textStyle = {TextStyle.None}, 
          textColor = {0, 85, 255}), Text(origin = {-20.0, -50.0}, 
          lineColor = {0, 85, 255}, 
          extent = {{-10.0, 10.0}, {10.0, -10.0}}, 
          textString = "A", 
          textStyle = {TextStyle.None}, 
          textColor = {0, 85, 255})}));
      equation 
        connect(fixed_T.flange, piston_T.support_b)
          annotation (Line(origin = {95.0, -20.0}, 
            points = {{5.0, 0.0}, {5.0, 10.0}, {-5.0, 10.0}}, 
            color = {0, 127, 0}));
        connect(piston_P.support_b, fixed_P.flange)
          annotation (Line(origin = {-95.0, -20.0}, 
            points = {{5.0, 10.0}, {-5.0, 10.0}, {-5.0, 0.0}}, 
            color = {0, 127, 0}));
        connect(piston_P.port_a, tank_P.port)
          annotation (Line(origin = {-85.0, -35.0}, 
            points = {{0.0, 25.0}, {0.0, -15.0}, {-1.0, -15.0}}, 
            color = {0, 127, 255}));
        connect(piston_P.flange_a, spool_P.flange_b)
          annotation (Line(origin = {-60.0, 0.0}, 
            points = {{-10.0, 0.0}, {10.0, 0.0}}, 
            color = {0, 127, 0}));
        connect(spool_P.support_b, piston_P.support_a)
          annotation (Line(origin = {-60.0, -10.0}, 
            points = {{10.0, 0.0}, {-10.0, 0.0}}, 
            color = {0, 127, 0}));
        connect(spool_P.flange_a, mass.flange_a)
          annotation (Line(origin = {-20.0, 0.0}, 
            points = {{-10.0, 0.0}, {10.0, 0.0}}, 
            color = {0, 127, 0}));
        connect(mass.flange_b, spool_T.flange_a)
          annotation (Line(origin = {20.0, 0.0}, 
            points = {{-10.0, 0.0}, {10.0, 0.0}}, 
            color = {0, 127, 0}));
        connect(spool_T.flange_b, piston_T.flange_a)
          annotation (Line(origin = {60.0, 0.0}, 
            points = {{-10.0, 0.0}, {10.0, 0.0}}, 
            color = {0, 127, 0}));
        connect(spool_T.support_b, piston_T.support_a)
          annotation (Line(origin = {60.0, -10.0}, 
            points = {{-10.0, 0.0}, {10.0, 0.0}}, 
            color = {0, 127, 0}));
        connect(piston_T.port_a, tank_T.port)
          annotation (Line(origin = {85.0, -30.0}, 
            points = {{0.0, 20.0}, {0.0, -20.0}}, 
            color = {0, 127, 255}));
        connect(spool_P.port_b, port_A)
          annotation (Line(origin = {-19.0, -55.0}, 
            points = {{-19.0, 45.0}, {-19.0, -45.0}, {19.0, -45.0}}, 
            color = {0, 127, 255}));
        connect(spool_T.port_b, port_A)
          annotation (Line(origin = {19.0, -55.0}, 
            points = {{19.0, 45.0}, {19.0, -45.0}, {-19.0, -45.0}}, 
            color = {0, 127, 255}));
        connect(spool_P.port_a, port_P)
          annotation (Line(origin = {-53.0, 15.0}, 
            points = {{6.0, -25.0}, {6.0, -35.0}, {-7.0, -35.0}, {-7.0, 35.0}, {3.0, 35.0}}, 
            color = {0, 127, 255}));
        connect(spool_T.port_a, port_T)
          annotation (Line(origin = {54.0, 15.0}, 
            points = {{-7.0, -25.0}, {-7.0, -35.0}, {6.0, -35.0}, {6.0, 35.0}, {-4.0, 35.0}}, 
            color = {0, 127, 255}));
      end ThreeWaysValve;
      model AnnularOrificeSpool "带节流孔阀芯"
        // 阀孔水力直径使用x替代
        outer Media.OilMedia.Oil oil;

        // 结构参数
        parameter SI.Length ds(displayUnit = "mm") = 0.01 "筒径" 
          annotation (Dialog(group = "结构参数"));
        parameter SI.Length dr(displayUnit = "mm") = 0.005 "杆径" 
          annotation (Dialog(group = "结构参数"));
        parameter SI.Length x0(displayUnit = "mm") = 0 "初始阀芯位置" 
          annotation (Dialog(group = "结构参数"));
        parameter SI.Diameter Dh(displayUnit = "mm") = 0.005 "节流孔直径" 
          annotation (Dialog(group = "结构参数"));
        final parameter SI.Area area = pi / 4 * (ds ^ 2 - dr ^ 2) "活塞流通面积";

        parameter SI.Volume v0(displayUnit = "ml") = 0 "死区容积" 
          annotation (Dialog(group = "高级"));
        parameter SI.Angle theta = 1.20427718387609 "阀口射流角" 
          annotation (Dialog(group = "高级"));

        // 方法配置
        parameter Boolean reverse = false
          "true：反向放置 ‖ false：正向放置，默认为fasle" annotation (Dialog(group = "方法配置"));
        final parameter Integer switch = if reverse then -1 else 1 annotation (Evaluate = true);

        // 高级
        parameter Boolean UseJetForce = false "若为true，则考虑液动力，否则不考虑液动力" 
          annotation (Dialog(tab = "高级", group = "流体参数"));
        parameter Real Cqmax = 0.707 "最大流量系数" 
          annotation (Dialog(tab = "高级", group = "流体参数"));
        parameter Real lambda_crit = 1000 "临界流量数" 
          annotation (Dialog(tab = "高级", group = "流体参数"));

        // 变量
        SI.Diameter dh "水力直径";
        SI.Area area_orifice "阀孔通流面积";

        SI.Position x "阀芯相对壁面位移";
        // SI.Length length "腔体长度";
        SI.Velocity v "阀芯相对壁面速度";

        Real Cq "流量系数";
        Real lambda "流量数";
        SI.VolumeFlowRate qA "A口体积流量";
        SI.VolumeFlowRate qB "B口体积流量";
        SI.VolumeFlowRate q_vol "阀芯运动体积流量";

        SI.Force fst "稳态液动力";
        SI.Force fjet "液动力";
        Real kjet "液动力系数";
        SI.Force f "作用在flange_a正方向的力";

        SI.Pressure pA "A口压力";
        SI.Pressure pB "B口压力";
        SI.Pressure dp "端口压差";

        SI.Density rho "密度";
        SI.KinematicViscosity nu "运动粘度";

        // 接口
        OilPort_b port_b
          annotation (Placement(transformation(origin = {-20.0, -100.0}, 
            extent = {{-10.0, -10.0}, {10.0, 10.0}})));
        OilPort_a port_a
          annotation (Placement(transformation(origin = {70.0, -100.0}, 
            extent = {{-10.0, -10.0}, {10.0, 10.0}})));
        Flange_a flange_a
          annotation (Placement(transformation(origin = {-100.0, 0.0}, 
            extent = {{-10.0, -10.0}, {10.0, 10.0}})));
        Flange_a support_a
          annotation (Placement(transformation(origin = {-100.0, -100.0}, 
            extent = {{-10.0, -10.0}, {10.0, 10.0}})));
        Flange_a flange_b
          annotation (Placement(transformation(origin = {100.0, 0.0}, 
            extent = {{-10.0, -10.0}, {10.0, 10.0}})));
        Flange_a support_b
          annotation (Placement(transformation(origin = {100.0, -100.0}, 
            extent = {{-10.0, -10.0}, {10.0, 10.0}})));
        annotation (
          Icon(coordinateSystem(extent = {{-100.0, -100.0}, {100.0, 100.0}}, 
            grid = {2.0, 2.0}), graphics = {Polygon(origin = {-65.75, -80.305}, 
            fillColor = {192, 192, 192}, 
            fillPattern = FillPattern.Solid, 
            points = {{-34.25, 4.30500000000001}, {-14.25, 20.305}, {25.75, 20.305}, {25.74, 8.765}, {34.25, 8.795}, {34.19, -20.305}, {-34.24, -20.295}}, 
            thickness = 0.5), Polygon(origin = {25.8635, -80.245}, 
            fillColor = {192, 192, 192}, 
            fillPattern = FillPattern.Solid, 
            points = {{-25.8635, 20.245}, {34.1365, 20.245}, {34.1765, -19.975}, {-34.1765, -20.245}, {-33.8635, 8.245}, {-25.8635, 8.245}}, 
            thickness = 0.5), Polygon(origin = {90.31, -79.7}, 
            fillColor = {192, 192, 192}, 
            fillPattern = FillPattern.Solid, 
            points = {{-9.39, 20.76}, {9.69, 3.69999999999999}, {9.56, -20.73}, {-9.69, -20.76}}, 
            thickness = 0.5), Rectangle(origin = {0.0, 0.0}, 
            fillColor = {102, 102, 102}, 
            pattern = LinePattern.None, 
            fillPattern = FillPattern.Solid, 
            extent = {{-100.0, 4.0}, {100.0, -4.0}}, 
            thickness = 0.5), Line(origin = {50.1602328767123, 40.3847315068493}, 
            points = {{9.0, 0.0}, {-9.0, 0.0}}, 
            color = {255, 0, 0}, 
            thickness = 3.0, 
            arrow = {Arrow.None, Arrow.Filled}), Line(origin = {49.7815397260274, -40.0}, 
            points = {{9.0, 0.0}, {-9.0, 0.0}}, 
            color = {255, 0, 0}, 
            thickness = 3.0, 
            arrow = {Arrow.None, Arrow.Filled}), Rectangle(origin = {-35.0, 0.0}, 
            fillColor = {102, 102, 102}, 
            pattern = LinePattern.None, 
            fillPattern = FillPattern.Solid, 
            extent = {{-45.0, 60.0}, {45.0, -60.0}}), Polygon(origin = {0.0, 80.0}, 
            fillColor = {192, 192, 192}, 
            fillPattern = FillPattern.Solid, 
            points = {{-100.0, 2.0}, {-80.0, -20.0}, {-40.0, -20.0}, {-40.0, -10.0}, {0.0, -10.0}, {0.0, -20.0}, {80.0, -20.0}, {100.0, 0.0}, {100.0, 20.0}, {-100.0, 20.0}}, 
            thickness = 0.5)}));
      equation 
        // 接口方程
        pA = port_a.p;
        pB = port_b.p;
        dp = pA - pB;

        qA = port_a.q;
        qB = port_b.q;

        x = switch * (flange_a.s - support_a.s) + x0;
        flange_b.s = flange_a.s;
        support_b.s = support_a.s;

        flange_a.f + flange_b.f + switch * f = 0;
        support_a.f + support_b.f = 0;

        // 阀芯相对壁面速度
        // length = x;
        der(x) = v;

        // 通流面积方程
        dh = if x >= Dh then Dh else if x >= 0 then x else 0;
        area_orifice = pi / 4 * dh ^ 2;

        // 流量方程
        Cq = Cqmax * tanh(2 * lambda / lambda_crit);
        q_vol = v * area;
        qB = Cq * area_orifice * regRoot2(-dp, 5, 2 / rho, 2 / rho);
        qA + qB - q_vol = 0;

        // 流量数方程
        lambda = dh / nu * regRoot2(abs(dp), 5, 2 / rho, 2 / rho);

        // 液动力方程
        fst = max(0, 2 * Cq * area * dp * cos(theta));
        fjet = kjet * 1 / 2 * (tanh(2 * (dh / 1e-5)) + 1) * fst;
        kjet = if UseJetForce then 1 else 0;

        // 力平衡方程
        f = pA * area - fjet;

        // 物性方程
        rho = oil.rho;
        nu = oil.nu;
      end AnnularOrificeSpool;

      model SpringPiston
        // extends TwoFlangesOnePorts;

        outer Media.OilMedia.Oil oil;

        // 参数
        parameter SI.Diameter ds(displayUnit = "mm") = 0.01 "筒径" 
          annotation (Dialog(group = "结构参数"));
        parameter SI.Diameter dr(displayUnit = "mm") = 0.005 "杆径" 
          annotation (Dialog(group = "结构参数"));
        parameter SI.Length len0(displayUnit = "mm") = 0 "初始压力腔长度" 
          annotation (Dialog(group = "结构参数"));
        parameter SI.Volume v0(displayUnit = "ml") = 0 "死区容积" 
          annotation (Dialog(group = "结构参数"));
        final SI.Area area = pi / 4 * (ds ^ 2 - dr ^ 2) "活塞压力作用面积";

        // 方法配置
        parameter Boolean reverse = false
          "true：反向放置 ‖ false：正向放置，默认为fasle" annotation (Dialog(group = "方法配置"));
        final parameter Integer switch = if reverse then -1 else 1 annotation (Evaluate = true);

        // 弹簧参数
        parameter SI.Length s_0(displayUnit = "mm") = 0.01 "弹簧初始长度" 
          annotation (Dialog(tab = "弹簧参数"));
        parameter SI.TranslationalSpringConstant k = 1000 "弹簧刚度" 
          annotation (Dialog(tab = "弹簧参数"));

        // 变量
        SI.Length x "活塞位移";
        SI.Length length "腔体长度";
        SI.Velocity v "活塞运动速度";

        SI.Force fspr "弹簧力";
        SI.Force f "活塞受力";

        SI.VolumeFlowRate q "体积流量";
        SI.Pressure p "油压";

        // 接口
        OilPort_a port_a
          annotation (Placement(transformation(origin = {50.00000000000001, -100.0}, 
            extent = {{-10.0, -10.0}, {10.0, 10.0}})));
        Flange_a flange_a
          annotation (Placement(transformation(origin = {-100.0, 0.0}, 
            extent = {{-10.0, -10.0}, {10.0, 10.0}})));
        Flange_a support_a
          annotation (Placement(transformation(origin = {-100.0, -100.0}, 
            extent = {{-10.0, -10.0}, {10.0, 10.0}})));
        Flange_a flange_b
          annotation (Placement(transformation(origin = {100.0, 0.0}, 
            extent = {{-10.0, -10.0}, {10.0, 10.0}})));
        Flange_a support_b
          annotation (Placement(transformation(origin = {100.0, -100.0}, 
            extent = {{-10.0, -10.0}, {10.0, 10.0}})));
        annotation (
          Icon(coordinateSystem(extent = {{-100.0, -100.0}, {100.0, 100.0}}, 
            grid = {2.0, 2.0}), graphics = {Line(origin = {47.0, 0.0}, 
            points = {{-5.0, -60.0}, {5.0, 60.0}}, 
            thickness = 1.0), Line(origin = {27.0, 0.0}, 
            points = {{-5.0, -60.0}, {5.0, 60.0}}, 
            thickness = 1.0), Line(origin = {7.0, 0.0}, 
            points = {{-5.0, -60.0}, {5.0, 60.0}}, 
            thickness = 1.0), Rectangle(origin = {-22.0, 0.0}, 
            fillColor = {102, 102, 102}, 
            pattern = LinePattern.None, 
            fillPattern = FillPattern.Solid, 
            extent = {{-20.0, 70.0}, {20.0, -70.0}}, 
            thickness = 0.5), Line(origin = {66.0, -25.0}, 
            points = {{-4.0, -35.0}, {4.0, 35.0}}, 
            thickness = 1.0), Polygon(origin = {0.0, 55.0}, 
            fillColor = {192, 192, 192}, 
            fillPattern = FillPattern.Solid, 
            points = {{-100.0, 45.0}, {-88.0, 45.0}, {100.0, 45.0}, {100.0, -45.0}, {70.0, -45.0}, {70.0, 15.0}, {-100.0, 15.0}}), Polygon(origin = {80.0, -55.0}, 
            fillColor = {192, 192, 192}, 
            fillPattern = FillPattern.Solid, 
            points = {{-10.0, 45.0}, {-10.0, -15.0}, {-20.0, -15.0}, {-20.0, -45.0}, {20.0, -45.0}, {20.0, 45.0}}), Rectangle(origin = {-31.0, -85.0}, 
            fillColor = {192, 192, 192}, 
            fillPattern = FillPattern.Solid, 
            extent = {{-71.0, -15.0}, {71.0, 15.0}}, 
            thickness = 0.5), Line(origin = {17.0, 0.0}, 
            points = {{-5.0, 60.0}, {5.0, -60.0}}, 
            thickness = 1.0), Line(origin = {37.0, 0.0}, 
            points = {{-5.0, 60.0}, {5.0, -60.0}}, 
            thickness = 1.0), Line(origin = {57.0, 0.0}, 
            points = {{-5.0, 60.0}, {5.0, -60.0}}, 
            thickness = 1.0), Line(origin = {29.0, 40.0}, 
            points = {{9.0, 0.0}, {-9.0, 0.0}}, 
            color = {255, 0, 0}, 
            thickness = 3.0, 
            arrow = {Arrow.None, Arrow.Filled}), Line(origin = {29.0, -40.0}, 
            points = {{9.0, 0.0}, {-9.0, 0.0}}, 
            color = {255, 0, 0}, 
            thickness = 3.0, 
            arrow = {Arrow.None, Arrow.Filled}), Line(origin = {-2.0, 0.0}, 
            points = {{0.0, 70.0}, {0.0, -70.0}}, 
            thickness = 1.0), Rectangle(origin = {0.0, 0.0}, 
            fillColor = {102, 102, 102}, 
            pattern = LinePattern.None, 
            fillPattern = FillPattern.Solid, 
            extent = {{-100.0, 4.0}, {100.0, -4.0}}, 
            thickness = 0.5)}));
      equation 
        // 接口方程
        p = port_a.p;
        q = -port_a.q;

        x = switch * (flange_a.s - support_a.s) + len0;
        flange_b.s = flange_a.s;
        support_b.s = support_a.s;

        flange_a.f + flange_b.f + switch * f = 0;
        support_a.f + support_b.f = 0;

        // 腔体长度
        length = noEvent(max(x, 0));
        v = der(length);

        // 流量方程
        q = v * area;

        // 力平衡方程
        f = -fspr + p * area;
        fspr = k * (length - s_0);
      end SpringPiston;

      model BoundaryPressure_oil "压力边界（油液）"

        parameter SI.AbsolutePressure p = 1e5 "压力" 
          annotation (Evaluate = true, 
            Dialog(enable = not use_p_in));
        // parameter SI.Temperature T = 298.15 "温度" 
        //   annotation (Evaluate = true, 
        //     Dialog(enable = not use_T_in));

        parameter Boolean use_p_in = false "压力由外部接口输入" 
          annotation (Dialog(group = "数据来源选项"), Evaluate = true, HideResult = true, choices(checkBox = true));
        // parameter Boolean use_T_in = false "温度由外部接口输入" 
        //   annotation (Dialog(group = "数据来源选项"), Evaluate = true, HideResult = true, choices(checkBox = true));

        Modelica.Blocks.Interfaces.RealInput p_in if use_p_in "外部给定压力" 
          annotation (Placement(transformation(
            origin = {-60, 100}, 
            extent = {{-20, -20}, {20, 20}}, 
            rotation = 270)));
        // Modelica.Blocks.Interfaces.RealInput T_in if use_T_in "外部给定温度" 
        //   annotation (Placement(transformation(origin = {0.0, 100.0}, 
        //     extent = {{-20.0, -20.0}, {20.0, 20.0}}, 
        //     rotation = 270.0)));

        OilPort_b oilPort
          annotation (Placement(transformation(origin = {100.0, 0.0}, 
            extent = {{-10.0, -10.0}, {10.0, 10.0}})));
      protected 
        Modelica.Blocks.Interfaces.RealInput p_in_internal
          "用于连接外部有条件的连接";
        // Modelica.Blocks.Interfaces.RealInput T_in_internal
        //   "用于连接外部有条件的连接";
      equation 
        connect(p_in, p_in_internal);
        // connect(T_in, T_in_internal);

        if not use_p_in then 
          p_in_internal = p;
        end if;
        // if not use_T_in then 
        //   T_in_internal = T;
        // end if;
        //////////////
        oilPort.p = p_in_internal;
        // if not In then
        //   fluidPort.T = T_in_internal;
        // end if;
        // fluidPort.T = T_in_internal;
        annotation (
          Icon(coordinateSystem(extent = {{-100.0, -100.0}, {100.0, 100.0}}, 
            grid = {2.0, 2.0}), graphics = {Ellipse(origin = {0.0, 0.0}, 
            lineColor = {66, 132, 197}, 
            fillColor = {0, 85, 255}, 
            fillPattern = FillPattern.Solid, 
            extent = {{-80.0, 80.0}, {80.0, -80.0}}), Text(origin = {2.0, 7.0}, 
            lineColor = {255, 255, 255}, 
            extent = {{-52.0, 53.0}, {52.0, -53.0}}, 
            textString = "p", 
            textStyle = {TextStyle.None}, 
            textColor = {255, 255, 255}), Line(origin = {90.0, 0.0}, 
            points = {{-10.0, 0.0}, {10.0, 0.0}}, 
            color = {0, 85, 255}, 
            thickness = 2.0)}));
      end BoundaryPressure_oil;
      model Oil "油液介质"
        parameter SI.Temperature T0 = 300;
        parameter SI.Density rho = 865 "油液密度";
        parameter SI.BulkModulus B = 1.8e9 "体积模量";
        parameter SI.Pressure p_atm = 1e+5 "大气压力";
        parameter SI.Pressure p_vapour = 1e+3 "蒸发压力";
        parameter SI.KinematicViscosity nu = 46e-6;
        parameter SI.DynamicViscosity mu = rho * nu;
        annotation (defaultComponentName = "oil", 
          defaultComponentPrefixes = "inner", 
          missingInnerMessage = "No oil is defined.", 
          Diagram(coordinateSystem(extent = {{-140.0, -100.0}, {140.0, 100.0}}, 
            preserveAspectRatio = false, 
            grid = {2.0, 2.0})), 
          Icon(coordinateSystem(extent = {{-100.0, -100.0}, {100.0, 100.0}}, 
            preserveAspectRatio = false, 
            grid = {2.0, 2.0}), graphics = {Ellipse(origin = {0.0, 17.0}, 
            lineColor = {0, 85, 255}, 
            fillColor = {255, 255, 255}, 
            extent = {{-64.0, 63.0}, {64.0, -63.0}}), Polygon(origin = {0.0, 33.0}, 
            lineColor = {0, 85, 255}, 
            fillColor = {0, 85, 255}, 
            fillPattern = FillPattern.Solid, 
            points = {{0.0, 27.0}, {-24.0, -27.0}, {24.0, -27.0}}), Ellipse(origin = {0.0, -1.0}, 
            lineColor = {0, 85, 255}, 
            fillColor = {0, 85, 255}, 
            fillPattern = FillPattern.Solid, 
            extent = {{-26.0, 25.0}, {26.0, -25.0}})}));
      end Oil;
      model BoundarySpeed_N "转速边界"
        parameter SI.AngularVelocity N(displayUnit = "rpm") = 1 "固定转速" 
          annotation (Dialog(enable = not use_N_in));
        parameter Boolean use_N_in = false "转速由外部接口输入" 
          annotation (Dialog(group = "数据来源选项"), Evaluate = true, HideResult = true, choices(checkBox = true));

        Modelica.Blocks.Interfaces.RealInput N_in(unit = "rpm") if use_N_in "转速" 
          annotation (Placement(transformation(extent = {{-140, -20}, {-100, 20}}, rotation = 0)));
        PowerPort_a flange
          annotation (Placement(transformation(origin = {100.0, 0.0}, 
            extent = {{-10.0, -10.0}, {10.0, 10.0}})));
      protected 
        Modelica.Blocks.Interfaces.RealInput N_in_internal
          "用于连接外部有条件的连接";
      equation 
        N_in_internal = der(flange.phi);

        connect(N_in, N_in_internal);
        if not use_N_in then 
          N_in_internal = N;
        end if;
        annotation (
          Icon(coordinateSystem(extent = {{-100.0, -100.0}, {100.0, 100.0}}, 
            grid = {2.0, 2.0}), graphics = {Text(origin = {0.0, 90.0}, 
            lineColor = {0, 0, 255}, 
            extent = {{-150.0, 20.0}, {150.0, -20.0}}, 
            textString = "%name", 
            textColor = {0, 0, 255}), Line(origin = {-1.0, 31.0}, 
            points = {{-87.0, -31.0}, {-63.0, -1.0}, {-35.0, 21.0}, {-1.0, 31.0}, {29.0, 25.0}, {49.0, 13.0}, {65.0, -3.0}, {77.0, -17.0}, {87.0, -31.0}}, 
            thickness = 0.5, 
            smooth = Smooth.Bezier), Polygon(origin = {61.5, 29.0}, 
            fillPattern = FillPattern.Solid, 
            points = {{24.5, -29.0}, {4.5, 29.0}, {-24.5, -2.0}, {24.5, -29.0}}), Line(origin = {0.0, -30.0}, 
            points = {{-30.0, 0.0}, {30.0, 0.0}}), Line(origin = {-20.0, -40.0}, 
            points = {{-10.0, -10.0}, {10.0, 10.0}}), Line(origin = {0.0, -40.0}, 
            points = {{-10.0, -10.0}, {10.0, 10.0}}), Line(origin = {20.0, -40.0}, 
            points = {{-10.0, -10.0}, {10.0, 10.0}}), Line(origin = {5.0, -36.0}, 
            points = {{-59.0, -6.0}, {-43.0, 8.0}, {-21.0, 20.0}, {-1.0, 22.0}, {17.0, 18.0}, {31.0, 10.0}, {43.0, 0.0}, {51.0, -10.0}, {59.0, -22.0}}, 
            smooth = Smooth.Bezier), Polygon(origin = {-52.5, -51.0}, 
            fillPattern = FillPattern.Solid, 
            points = {{-8.5, -15.0}, {8.5, 9.0}, {-5.5, 15.0}, {-8.5, -15.0}}), Text(origin = {103.455, 12.9394}, 
            extent = {{-125.45454545454545, -28.939393939393938}, {-79.45454545454545, 11.060606060606062}}, 
            textString = "N", 
            textStyle = {TextStyle.None}), Line(origin = {0.0, -55.5}, 
            points = {{0.0, 35.5}, {0.0, -35.5}})}), Documentation(info = "<HTML>
<p>
Model of <b>fixed</b> angular velocity of flange, not dependent on torque.
</p>
</HTML>"), 
          Icon(coordinateSystem(extent = {{-100.0, -100.0}, {100.0, 100.0}}, 
            grid = {2.0, 2.0})), 
          Documentation(info = "<HTML>
<p>
Partial model of torque that accelerates the flange.
</p>

<p>
If <i>useSupport=true</i>, the support connector is conditionally enabled
and needs to be connected.<br>
If <i>useSupport=false</i>, the support connector is conditionally disabled
and instead the component is internally fixed to ground.
</p>
</html>"));
      end BoundarySpeed_N;
      function regRoot2
        "Anti-symmetric approximation of square root with discontinuous factor so that the first derivative is finite and continuous"

        input Real x "abscissa value";
        input Real x_small(min = 0) = 0.01
          "approximation of function for |x| <= x_small";
        input Real k1(min = 0) = 1
          "y = if x>=0 then sqrt(k1*x) else -sqrt(k2*|x|)";
        input Real k2(min = 0) = 1
          "y = if x>=0 then sqrt(k1*x) else -sqrt(k2*|x|)";
        input Boolean use_yd0 = false "= true, if yd0 shall be used";
        input Real yd0(min = 0) = 1 "Desired derivative at x=0: dy/dx = yd0";
        output Real y "ordinate value";
      protected 
        encapsulated function regRoot2_utility
          "Interpolating with two 3-order polynomials with a prescribed derivative at x=0"
          import Modelica_Network.YJNY.Pump.Basic.evaluatePoly3_derivativeAtZero;

          input Real x;
          input Real x1 "approximation of function abs(x) < x1";
          input Real k1
            "y = if x>=0 then sqrt(k1*x) else -sqrt(k2*|x|); k1 >= k2";
          input Real k2 "y = if x>=0 then sqrt(k1*x) else -sqrt(k2*|x|))";
          input Boolean use_yd0 "= true, if yd0 shall be used";
          input Real yd0(min = 0) "Desired derivative at x=0: dy/dx = yd0";
          output Real y;
        protected 
          Real x2;
          Real xsqrt1;
          Real xsqrt2;
          Real y1;
          Real y2;
          Real y1d;
          Real y2d;
          Real w;
          Real y0d;
          Real w1;
          Real w2;
        algorithm 
          if k2 > 0 then 
            x2 := -x1 * (k2 / k1);
          else
            x2 := -x1;
          end if;
          if x <= x2 then 
            y := -sqrt(k2 * abs(x));
          else
            y1 := sqrt(k1 * x1);
            y2 := -sqrt(k2 * abs(x2));
            y1d := sqrt(k1 / x1) / 2;
            y2d := sqrt(k2 / abs(x2)) / 2;

            if use_yd0 then 
              y0d := yd0;
            else
              w := x2 / x1;
              y0d := ((3 * y2 - x2 * y2d) / w - (3 * y1 - x1 * y1d) * w) / (2 * x1 * (1 - w));
            end if;
            /* Modify derivative y0d, such that the polynomial ismonotonically increasing. A sufficient condition is*/
            w1 := sqrt(8.75 * k1 / x1);
            w2 := sqrt(8.75 * k2 / abs(x2));
            y0d := min(y0d, 0.9 * min(w1, w2));
            /* Perform interpolation in scaled polynomial: y_new = y/y1 x_new = x/x1*/
            y := y1 * (if x >= 0 then evaluatePoly3_derivativeAtZero(x / x1, 1, 1, y1d * x1 / y1, y0d * x1 / y1) else 
              evaluatePoly3_derivativeAtZero(x / x1, x2 / x1, y2 / y1, y2d * x1 / y1, y0d * x1 / y1));
          end if;
          annotation (smoothOrder = 2);
        end regRoot2_utility;
      algorithm 
        y := smooth(2, if x >= x_small then sqrt(k1 * x) else 
          if x <= -x_small then -sqrt(k2 * abs(x)) else 
          if k1 >= k2 then regRoot2_utility(x, x_small, k1, k2, use_yd0, yd0) else 
          -regRoot2_utility(-x, x_small, k2, k1, use_yd0, yd0));
        annotation (smoothOrder = 2);
      end regRoot2;
      function evaluatePoly3_derivativeAtZero
        "Evaluate polynomial of order 3 that passes the origin with a predefined derivative"
        input Real x "Value for which polynomial shall be evaluated";
        input Real x1 "Abscissa value";
        input Real y1 "y1=f(x1)";
        input Real y1d "First derivative at y1";
        input Real y0d "First derivative at f(x=0)";
        output Real y;
      protected 
        Real a1;
        Real a2;
        Real a3;
        Real xx;
      algorithm 
        a1 := x1 * y0d;
        a2 := 3 * y1 - x1 * y1d - 2 * a1;
        a3 := y1 - a2 - a1;
        xx := x / x1;
        y := xx * (a1 + xx * (a2 + xx * a3));
        annotation (smoothOrder = 3);
      end evaluatePoly3_derivativeAtZero;
    end Basic;



    model MotorPump "恒压变量泵"



      Modelica_Network.YJNY.Pump.Basic.Tank tank3(p0 = 0)
        annotation (Placement(transformation(origin = {-1.4210854715202004e-14, -69.99999999999999}, 
          extent = {{-10.0, -10.0}, {10.0, 10.0}})));



      VariableDisplacementPump_ConstP2 variableDisplacementPump_ConstP(phi0 = 0.0349065850398866, 
        actingCylinder(volume_A(pstart
           = 0), 
          volume_B(pstart
             = 0)), 
        ds = 0.03) annotation (Placement(transformation(extent = {{-20.0, -20.0}, {20.0, 20.0}})));



      Modelica.Blocks.Sources.Trapezoid ramp(
        offset = 1e5, startTime = 10, 
        amplitude = 2e5, rising = 10, width = 10, falling = 10, 
        period = 40)


        annotation (Placement(transformation(origin = {50.0, 65.99999999999999}, 
          extent = {{10.0, -10.0}, {-10.0, 10.0}})));
      Basic.BoundaryPressure_oil boundaryPressure_oil(use_p_in = true) annotation (Placement(transformation(origin = {0.0, 59.999999999999986}, 
        extent = {{-10.0, -10.0}, {10.0, 10.0}}, 
        rotation = -90.0)));



      annotation (Diagram(coordinateSystem(extent = {{-200.0, -200.0}, {200.0, 200.0}}, 
        grid = {2.0, 2.0})), 
        Icon(coordinateSystem(extent = {{-200.0, -200.0}, {200.0, 200.0}}, 
          grid = {2.0, 2.0}), graphics = {Rectangle(origin = {-147.0, 0.0}, 
          fillColor = {255, 255, 255}, 
          fillPattern = FillPattern.Solid, 
          extent = {{-53.0, 10.0}, {53.0, -10.0}}), Ellipse(origin = {0.0, 0.0}, 
          fillColor = {255, 255, 255}, 
          fillPattern = FillPattern.Solid, 
          extent = {{-100.0, 100.0}, {100.0, -100.0}}), Line(origin = {0.0, 150.0}, 
          points = {{0.0, -50.0}, {0.0, 50.0}}, 
          thickness = 1.0), Line(origin = {0.0, -150.0}, 
          points = {{0.0, -50.0}, {0.0, 50.0}}, 
          thickness = 1.0), Polygon(origin = {0.0, 78.00000000000003}, 
          fillColor = {0, 0, 0}, 
          fillPattern = FillPattern.Solid, 
          points = {{0.0, 20.0}, {-40.0, -20.0}, {40.0, -20.0}}), Line(origin = {0.0, 0.0}, 
          points = {{140.0, -140.0}, {-140.0, 140.0}}, 
          thickness = 1.0, 
          arrow = {Arrow.None, Arrow.Filled}, 
          arrowSize = 19.0), Line(origin = {170.0, -170.0}, 
          points = {{4.0, 30.0}, {-30.0, 20.0}, {30.0, 10.0}, {-30.0, 0.0}, {26.0, -8.0}, {-30.0, -20.0}, {10.0, -30.0}}, 
          thickness = 1.0), Rectangle(origin = {170.0, -90.0}, 
          fillColor = {255, 255, 255}, 
          fillPattern = FillPattern.Solid, 
          lineThickness = 1.0, 
          extent = {{-30.0, 50.0}, {30.0, -50.0}}), Polygon(origin = {170.0, -60.0}, 
          fillColor = {0, 85, 255}, 
          fillPattern = FillPattern.Solid, 
          points = {{-30.0, 20.0}, {30.0, 20.0}, {0.0, -20.0}}), Line(origin = {85.0, 60.0}, 
          points = {{85.0, -100.0}, {85.0, 100.0}, {-25.0, 100.0}, {-85.0, 40.0}}, 
          pattern = LinePattern.Dash, 
          thickness = 1.0), Text(origin = {0.0, 240.0}, 
          lineColor = {0, 128, 0}, 
          extent = {{-200.0, 40.0}, {200.0, -40.0}}, 
          textString = "%name", 
          fontName = "Times New Roman", 
          textStyle = {TextStyle.None}, 
          textColor = {0, 128, 0})}), 
        Protection(access = Access.diagram));
      Basic.PowerPort_a rotor "转轴接口" 
        annotation (Placement(transformation(origin = {-199.99999999999994, 4.440892098500626e-16}, 
          extent = {{-10.0, -10.0}, {10.0, 10.0}})));
      inner Basic.Oil oil annotation (Placement(transformation(origin = {-96.0, 60.0}, 
        extent = {{-10.0, -10.0}, {10.0, 10.0}})));
    equation 
      connect(variableDisplacementPump_ConstP.port_In, tank3.port)
        annotation (Line(origin = {0.0, -45.0}, 
          points = {{0.0, 25.0}, {0.0, -25.0}}, 
          color = {0, 127, 255}));
      connect(variableDisplacementPump_ConstP.port_Out, boundaryPressure_oil.oilPort)
        annotation (Line(origin = {0.0, 34.0}, 
          points = {{0.0, -14.0}, {0.0, 16.0}}, 
          color = {0, 127, 255}));
      connect(boundaryPressure_oil.p_in, ramp.y)
        annotation (Line(origin = {25.0, 66.0}, 
          points = {{-15.0, 0.0}, {14.0, 0.0}}, 
          color = {0, 0, 127}));
      connect(variableDisplacementPump_ConstP.rotor, rotor)
        annotation (Line(origin = {-69.0, 0.0}, 
          points = {{49.0, 0.0}, {-131.0, 0.0}}, 
          color = {0, 0, 0}));
    end MotorPump;
    model Pump "变量泵"
      MotorPump motorPump
        annotation (Placement(transformation(origin = {23.999999999999993, 0.0}, 
          extent = {{-20.0, -20.0}, {20.0, 20.0}})));
      Basic.BoundarySpeed_N boundarySpeed_N(N = 10.471975511966, 
        use_N_in = true) annotation (Placement(transformation(origin = {-18.0, 0.0}, 
          extent = {{-10.0, -10.0}, {10.0, 10.0}})));
      annotation (Diagram(coordinateSystem(extent = {{-200.0, -200.0}, {200.0, 200.0}}, 
        grid = {2.0, 2.0})), 
        Icon(coordinateSystem(extent = {{-200.0, -200.0}, {200.0, 200.0}}, 
          grid = {2.0, 2.0}), graphics = {Rectangle(origin = {-147.0, 0.0}, 
          fillColor = {255, 255, 255}, 
          fillPattern = FillPattern.Solid, 
          extent = {{-53.0, 10.0}, {53.0, -10.0}}), Ellipse(origin = {0.0, 0.0}, 
          fillColor = {255, 255, 255}, 
          fillPattern = FillPattern.Solid, 
          extent = {{-100.0, 100.0}, {100.0, -100.0}}), Line(origin = {0.0, 150.0}, 
          points = {{0.0, -50.0}, {0.0, 50.0}}, 
          thickness = 1.0), Line(origin = {0.0, -150.0}, 
          points = {{0.0, -50.0}, {0.0, 50.0}}, 
          thickness = 1.0), Polygon(origin = {0.0, 78.00000000000003}, 
          fillColor = {0, 0, 0}, 
          fillPattern = FillPattern.Solid, 
          points = {{0.0, 20.0}, {-40.0, -20.0}, {40.0, -20.0}}), Line(origin = {0.0, 0.0}, 
          points = {{140.0, -140.0}, {-140.0, 140.0}}, 
          thickness = 1.0, 
          arrow = {Arrow.None, Arrow.Filled}, 
          arrowSize = 19.0), Line(origin = {170.0, -170.0}, 
          points = {{4.0, 30.0}, {-30.0, 20.0}, {30.0, 10.0}, {-30.0, 0.0}, {26.0, -8.0}, {-30.0, -20.0}, {10.0, -30.0}}, 
          thickness = 1.0), Rectangle(origin = {170.0, -90.0}, 
          fillColor = {255, 255, 255}, 
          fillPattern = FillPattern.Solid, 
          lineThickness = 1.0, 
          extent = {{-30.0, 50.0}, {30.0, -50.0}}), Polygon(origin = {170.0, -60.0}, 
          fillColor = {0, 85, 255}, 
          fillPattern = FillPattern.Solid, 
          points = {{-30.0, 20.0}, {30.0, 20.0}, {0.0, -20.0}}), Line(origin = {85.0, 60.0}, 
          points = {{85.0, -100.0}, {85.0, 100.0}, {-25.0, 100.0}, {-85.0, 40.0}}, 
          pattern = LinePattern.Dash, 
          thickness = 1.0), Text(origin = {0.0, 240.0}, 
          lineColor = {0, 128, 0}, 
          extent = {{-200.0, 40.0}, {200.0, -40.0}}, 
          textString = "%name", 
          fontName = "Times New Roman", 
          textStyle = {TextStyle.None}, 
          textColor = {0, 128, 0})}), 
        Protection(access = Access.diagram));
      Modelica.Mechanics.Rotational.Sensors.SpeedSensor speedSensor
        annotation (Placement(transformation(origin = {-70.0, 0.0}, 
          extent = {{-10.0, -10.0}, {10.0, 10.0}})));
      Modelica.Mechanics.Rotational.Interfaces.Flange_a flange "Flange of shaft from which sensor information shall be measured" 
        annotation (Placement(transformation(origin = {-200.0, 0.0}, 
          extent = {{-10.0, -10.0}, {10.0, 10.0}})));
      Modelica.Blocks.Interfaces.RealOutput L "流量" 
        annotation (Placement(transformation(origin = {199.99999999999997, 60.000000000000014}, 
          extent = {{-10.0, -10.0}, {10.0, 10.0}})));
      Modelica.Blocks.Interfaces.RealOutput P "压力" 
        annotation (Placement(transformation(origin = {200.0, 0.0}, 
          extent = {{-10.0, -10.0}, {10.0, 10.0}})));
      Modelica.Blocks.Interfaces.RealOutput y "效率" 
        annotation (Placement(transformation(origin = {200.00000000000003, -59.999999999999986}, 
          extent = {{-10.0, -10.0}, {10.0, 10.0}})));
    equation 
      L = motorPump.variableDisplacementPump_ConstP.port_In.q * 3000;
      P = motorPump.variableDisplacementPump_ConstP.port_Out.p;
      y = 0.92;
      connect(boundarySpeed_N.flange, motorPump.rotor)
        annotation (Line(origin = {-21.000000000000014, 0.0}, 
          points = {{13.0, 0.0}, {25.0, 0.0}}, 
          color = {0, 0, 0}));
      connect(speedSensor.w, boundarySpeed_N.N_in)
        annotation (Line(origin = {-44.0, 0.0}, 
          points = {{-15.0, 0.0}, {14.0, 0.0}}, 
          color = {0, 0, 127}));
      connect(speedSensor.flange, flange)
        annotation (Line(origin = {-139.0, 0.0}, 
          points = {{59.0, 0.0}, {-61.0, 0.0}}, 
          color = {0, 0, 0}));
    end Pump;
  end Pump;
  package Test

    model TestDemo01 "蓄电池测试"
      extends Modelica.Icons.Example;
      Battery.Battery battery
        annotation (Placement(transformation(origin = {-50.0, 50.0}, 
          extent = {{10.0, -10.0}, {-10.0, 10.0}})));



      Modelica.Electrical.Analog.Basic.Ground ground
        annotation (Placement(transformation(origin = {50.0, -30.0}, 
          extent = {{-10.0, -10.0}, {10.0, 10.0}})));
      annotation (Protection(access = Access.diagram), 
        experiment(Algorithm = Dassl, NumberOfIntervals = 500, StartTime = 0, StopTime = 30, Tolerance = 0.0001));
    equation 
      connect(ground.p, battery.n)
        annotation (Line(origin = {5.0, 13.0}, 
          points = {{45.0, -33.0}, {45.0, 32.0}, {-45.0, 32.0}}, 
          color = {0, 0, 255}));
    end TestDemo01;
    model Model1
      Battery.Battery battery
        annotation (Placement(transformation(origin = {-50.00000000000001, 50.00000000000001}, 
          extent = {{10.0, -10.0}, {-10.0, 10.0}})));



      Modelica.Electrical.Analog.Basic.Ground ground
        annotation (Placement(transformation(origin = {-30.000000000000014, 10.000000000000007}, 
          extent = {{-10.0, -10.0}, {10.0, 10.0}})));
      APU.APU aPU
        annotation (Placement(transformation(origin = {30.0, 50.000000000000014}, 
          extent = {{-10.0, -10.0}, {10.0, 10.0}})));
      Battery.Machines.Machines_P machines_P
        annotation (Placement(transformation(origin = {-2.0000000000000004, 10.000000000000007}, 
          extent = {{-10.0, -10.0}, {10.0, 10.0}})));
      Pump.Pump pump
        annotation (Placement(transformation(origin = {70.0, 10.0}, 
          extent = {{-10.0, -10.0}, {10.0, 10.0}})));
      Modelica.Blocks.Tables.CombiTable1D combiTable1D(table = {{0, 16}, {5, 11.5}, {12, 8}, {18, 6}, {100, 6}})
        annotation (Placement(transformation(origin = {-50.000000000000014, 90.0}, 
          extent = {{-10.0, -10.0}, {10.0, 10.0}})));
      Modelica.Blocks.Tables.CombiTable1D combiTable1D1(table = {{0, 0.5}, {5, 0.45}, {12, 0.48}, {18, 0.4}, {100, 0.45}})
        annotation (Placement(transformation(origin = {-10.0, 90.0}, 
          extent = {{-10.0, -10.0}, {10.0, 10.0}})));
      Modelica.Blocks.Sources.RealExpression realExpression(y = time)
        annotation (Placement(transformation(origin = {-90.0, 70.0}, 
          extent = {{-10.0, -10.0}, {10.0, 10.0}})));
      annotation (experiment(Algorithm = Dassl, NumberOfIntervals = 500, StartTime = 0, StopTime = 100, Tolerance = 0.0001));
      Modelica.Blocks.Sources.RealExpression realExpression1(y = 1)
        annotation (Placement(transformation(origin = {22.00000000000003, 82.0}, 
          extent = {{-10.0, -10.0}, {10.0, 10.0}})));
    equation 
      connect(ground.p, battery.n)
        annotation (Line(origin = {-35.0, 33.0}, 
          points = {{5.0, -13.0}, {5.0, 12.0}, {-5.0, 12.0}}, 
          color = {0, 0, 255}));
      connect(machines_P.p, battery.p)
        annotation (Line(origin = {-23.0, 38.0}, 
          points = {{17.0, -18.0}, {17.0, 17.0}, {-17.0, 17.0}}, 
          color = {0, 0, 255}));
      connect(machines_P.pin_an, battery.n)
        annotation (Line(origin = {-19.0, 33.0}, 
          points = {{21.0, -13.0}, {21.0, 12.0}, {-21.0, 12.0}}, 
          color = {0, 0, 255}));
      connect(machines_P.flange, aPU.flange)
        annotation (Line(origin = {22.0, 26.0}, 
          points = {{-14.0, -16.0}, {24.0, -16.0}, {24.0, 24.0}, {18.0, 24.0}}, 
          color = {0, 0, 0}));
      connect(pump.flange, machines_P.flange)
        annotation (Line(origin = {28.0, 10.0}, 
          points = {{32.0, 0.0}, {-20.0, 0.0}}, 
          color = {0, 0, 0}));
      connect(realExpression.y, combiTable1D.u[1])
        annotation (Line(origin = {-70.0, 80.0}, 
          points = {{-9.0, -10.0}, {-2.0, -10.0}, {-2.0, 10.0}, {8.0, 10.0}}, 
          color = {0, 0, 127}));
      connect(realExpression.y, combiTable1D1.u[1])
        annotation (Line(origin = {-50.0, 80.0}, 
          points = {{-29.0, -10.0}, {20.0, -10.0}, {20.0, 10.0}, {28.0, 10.0}}, 
          color = {0, 0, 127}));
      connect(combiTable1D.y[1], aPU.H)
        annotation (Line(origin = {-9.0, 72.0}, 
          points = {{-30.0, 18.0}, {-27.0, 18.0}, {-27.0, -8.0}, {19.0, -8.0}, {19.0, -18.0}, {29.0, -18.0}}, 
          color = {0, 0, 127}));
      connect(combiTable1D1.y[1], aPU.V)
        annotation (Line(origin = {11.0, 71.0}, 
          points = {{-10.0, 19.0}, {-5.0, 19.0}, {-5.0, -19.0}, {9.0, -19.0}}, 
          color = {0, 0, 127}));
      connect(realExpression1.y, aPU.Control)
        annotation (Line(origin = {30.0, 66.0}, 
          points = {{3.0, 16.0}, {16.0, 16.0}, {16.0, 6.0}, {-16.0, 6.0}, {-16.0, -16.0}, {-10.0, -16.0}}, 
          color = {0, 0, 127}));
    end Model1;
    model Model2
      Pump.Pump pump
        annotation (Placement(transformation(origin = {30.0, 10.0}, 
          extent = {{-20.0, -20.0}, {20.0, 20.0}})));
      Modelica.Mechanics.Rotational.Sources.Speed speed
        annotation (Placement(transformation(origin = {-30.0, 10.0}, 
          extent = {{-10.0, -10.0}, {10.0, 10.0}})));
      Modelica.Blocks.Sources.RealExpression realExpression(y = 100)
        annotation (Placement(transformation(origin = {-70.0, 10.0}, 
          extent = {{-10.0, -10.0}, {10.0, 10.0}})));
    equation 
      connect(speed.flange, pump.flange)
        annotation (Line(origin = {-5.0, 10.0}, 
          points = {{-15.0, 0.0}, {15.0, 0.0}}, 
          color = {0, 0, 0}));
      connect(realExpression.y, speed.w_ref)
        annotation (Line(origin = {-50.0, 10.0}, 
          points = {{-9.0, 0.0}, {8.0, 0.0}}, 
          color = {0, 0, 127}));
    end Model2;
    model Model3
      Battery.Battery battery
        annotation (Placement(transformation(origin = {-90.0, 50.0}, 
          extent = {{10.0, -10.0}, {-10.0, 10.0}})));



      Modelica.Electrical.Analog.Basic.Ground ground
        annotation (Placement(transformation(origin = {-70.00000000000001, 10.000000000000007}, 
          extent = {{-10.0, -10.0}, {10.0, 10.0}})));
      Battery.Machines.Machines_P machines_P
        annotation (Placement(transformation(origin = {-26.0, 10.00000000000001}, 
          extent = {{-10.0, -10.0}, {10.0, 10.0}})));
      Pump.Pump pump
        annotation (Placement(transformation(origin = {28.000000000000018, 10.00000000000001}, 
          extent = {{-20.0, -20.0}, {20.0, 20.0}})));
    equation 
      connect(ground.p, battery.n)
        annotation (Line(origin = {-75.0, 33.0}, 
          points = {{5.0, -13.0}, {5.0, 12.0}, {-5.0, 12.0}}, 
          color = {0, 0, 255}));
      connect(machines_P.p, battery.p)
        annotation (Line(origin = {-55.0, 38.0}, 
          points = {{25.0, -18.0}, {25.0, 17.0}, {-25.0, 17.0}}, 
          color = {0, 0, 255}));
      connect(machines_P.pin_an, battery.n)
        annotation (Line(origin = {-51.0, 33.0}, 
          points = {{29.0, -13.0}, {29.0, 12.0}, {-29.0, 12.0}}, 
          color = {0, 0, 255}));
      connect(pump.flange, machines_P.flange)
        annotation (Line(origin = {-4.0, 10.0}, 
          points = {{12.0, 0.0}, {-12.0, 0.0}}, 
          color = {0, 0, 0}));
    end Model3;
  end Test;
end YJNY;