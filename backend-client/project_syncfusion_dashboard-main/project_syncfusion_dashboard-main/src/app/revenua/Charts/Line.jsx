import React from 'react';

import { ChartsHeader, LineChart } from '../../../components';

const Line = (props) => {
  // console.log(props);
  return (
  <div className="m-4 md:m-10 mt-24 p-10 bg-white dark:bg-secondary-dark-bg rounded-3xl">
    <ChartsHeader category="Line" title="Sales Report Chart" />
    <div className="w-full">
      <LineChart 
      props = {props}
      />
    </div>
  </div>
);}

export default Line;
