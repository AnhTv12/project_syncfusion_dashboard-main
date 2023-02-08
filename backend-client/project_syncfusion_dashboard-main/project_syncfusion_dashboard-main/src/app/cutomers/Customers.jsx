import React, { useEffect, useState } from 'react';
import { GridComponent, ColumnsDirective, ColumnDirective, Page, Selection, Inject, Edit, Toolbar, Sort, Filter } from '@syncfusion/ej2-react-grids';
import { customersData ,customerGridImage,customerGridStatus} from '../../data/dummy';
import { Header } from '../../components';
import axios from 'axios';
import { useDispatch, useSelector } from 'react-redux';
import PulseLoader from "react-spinners/PulseLoader";
import { fetchUserData } from '../user/userSlice';

const Customers = () => {
  const dispatch = useDispatch();
  const selectionsettings = { persistSelection: true };
  const toolbarOptions = ['Delete'];
  const editing = { allowDeleting: true, allowEditing: true };
  const data = useSelector((state)=>state.user.data)
  const status = useSelector((state)=>state.user.status)
  const token = sessionStorage.getItem('id_token');
  let [loading, setLoading] = useState(false);
  let [color, setColor] = useState("#36d7b7");
  let isLoading = status == 'pending';
  console.log('loading', isLoading)
  console.log('data', data)
  useEffect(()=>{
    setLoading(false);

  },[]);
  useEffect(()=>{
    dispatch(fetchUserData());

  },[]);

  useEffect(() => {
    setLoading(isLoading);
}, [isLoading]);

  const customersGrid = [
    { type: 'checkbox', width: '50' },
    { headerText: 'Name',
      width: '150',
      template: customerGridImage,
      textAlign: 'Center' },
    { field: 'Status',
      headerText: 'Status',
      width: '130',
      format: 'yMd',
      textAlign: 'Center',
      template: customerGridStatus },
    { field: 'activeDay',
      headerText: 'Active Day',
      width: '100',
      format: 'yMMMM',
      textAlign: 'Center' },
  
    { field: 'branchId',
      headerText: 'Branch',
      width: '150',
      textAlign: 'Center' },
  
    { field: 'customerId',
      headerText: 'Customer ID',
      width: '120',
      textAlign: 'Center',
      isPrimaryKey: true,
    },
  
  ];

  return (
    <div className="m-2 md:m-10 mt-24 p-2 md:p-10 bg-white rounded-3xl">
      <Header category="Page" title="Customers" />
      <GridComponent
        dataSource={data}
        enableHover={false}
        allowPaging
        pageSettings={{ pageCount: 5 }}
        selectionSettings={selectionsettings}
        toolbar={toolbarOptions}
        editSettings={editing}
        allowSorting
      >
        {loading?(<PulseLoader
          color={color}
          loading={loading}
          cssOverride={{
            display: "block",
            margin: "0 auto",
          }}
          size={25}
          aria-label="Loading Spinner"
          data-testid="loader"
        />):(
        <ColumnsDirective>
          {/* eslint-disable-next-line react/jsx-props-no-spreading */}
          {customersGrid.map((item, index) => <ColumnDirective key={index} {...item} />)}
        </ColumnsDirective>)}
        <Inject services={[Page, Selection, Toolbar, Edit, Sort, Filter]} />
      </GridComponent>
    </div>
  );
};

export default Customers;
