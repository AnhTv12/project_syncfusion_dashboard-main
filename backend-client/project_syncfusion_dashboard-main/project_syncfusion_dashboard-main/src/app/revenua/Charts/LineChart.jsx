import React from 'react';
import { ChartComponent, SeriesCollectionDirective, SeriesDirective, Inject, LineSeries, DateTime, Legend, Tooltip } from '@syncfusion/ej2-react-charts';
import { useStateContext } from '../../../contexts/ContextProvider';

const LineChart = (props) => {
  const { currentMode } = useStateContext();
  const lineChartData = props.props.data;
  const dividers = props.props.dividers;
  const LinePrimaryXAxis ={
    valueType: 'DateTime',
    labelFormat: 'yMMMM',
    intervalType: props.props.timeInterval,
    edgeLabelPlacement: 'Shift',
    majorGridLines: { width: 0 },
    background: 'white',
  };

  const LinePrimaryYAxis = {
    labelFormat: '{value}$',
    rangePadding: 'None',
    interval: props.props.valueInterval,
    minimum: props.minValue,
    lineStyle: { width: 0 },
    majorTickLines: { width: 0 },
    minorTickLines: { width: 0 },
  };

  let lineCustomSeries = []
  if (dividers.length==0) {
    lineCustomSeries = [
      {
        dataSource: lineChartData[0],
        xName: 'x',
        yName: 'y',
        name: 'Total Sales',
        width: '2',
        marker: { visible: true, width: 5, height: 5 },
        type: 'Line'
      },

    ];
  }
  else {
    lineCustomSeries = dividers.map((item, index) => {
      return {
        dataSource: lineChartData[index],
        xName: 'x',
        yName: 'y',
        name: `${item.name}`,
        width: '2',
        marker: { visible: true, width: 5, height: 5 },
        type: 'Line'
      }
    })
  }
  console.log('line data: ', lineChartData, lineCustomSeries, dividers)

  return (
    <ChartComponent
      id="line-chart"
      height="420px"
      primaryXAxis={LinePrimaryXAxis}
      primaryYAxis={LinePrimaryYAxis}
      chartArea={{ border: { width: 0 } }}
      tooltip={{ enable: true }}
      background={currentMode === 'Dark' ? '#33373E' : '#fff'}
      legendSettings={{ background: 'white' }}
    >
      <Inject services={[LineSeries, DateTime, Legend, Tooltip]} />
      <SeriesCollectionDirective>
        {/* eslint-disable-next-line react/jsx-props-no-spreading */}
        {lineCustomSeries.map((item, index) => <SeriesDirective key={index} {...item} />)}
      </SeriesCollectionDirective>
    </ChartComponent>
  );
};

export default LineChart;
