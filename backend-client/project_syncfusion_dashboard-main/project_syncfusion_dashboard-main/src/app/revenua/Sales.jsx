import React, { useEffect, useState } from 'react'
import Select from 'react-select';
import { useStateContext } from '../../contexts/ContextProvider';
import Line from './Charts/Line';
import Pie from './Charts/Pie';
import { useDispatch, useSelector } from 'react-redux';
import { fetchData, resetData} from './revenueSlice';

import { lastRevenueDataSelector, servicesSlector, branchesSlector, statusSlector } from './selector'
import { changeChartType, changeStart, changeEnd, changeInterval, changeDivide } from './filterSlice';
import Area from './Charts/Area';
import Bar from './Charts/Bar';
import MoonLoader from "react-spinners/MoonLoader";

const Sales = () => {
    const dispatch = useDispatch();
    const { currentMode } = useStateContext();
    const data = useSelector(lastRevenueDataSelector);
    const filter = useSelector((state) => state.filter);
    const services = useSelector(servicesSlector);
    const branches = useSelector(branchesSlector);
    const status = useSelector(statusSlector)
    const [activeChart, setActiveChart] = useState(false);
    const [interval, setInterval] = useState('Years');
    const [divider, setDivider] = useState('None')
    const [dividers, setDividers] = useState([])
    let [loading, setLoading] = useState(false);
    let [color, setColor] = useState("#36d7b7");
    let isLoading = status == 'pending';
    useEffect(() => {
        dispatch(resetData())
        dispatch(changeChartType(null));
        dispatch(changeStart(null));
        dispatch(changeEnd(null));
        dispatch(changeInterval(null));
        dispatch(changeDivide(null));
        setActiveChart(false);
        setDividers([]);

    }, []);

    useEffect(() => {
        setLoading(isLoading);
    }, [isLoading])
    const dataSource1 = []
    data.forEach(item => {
        dataSource1.push(item.flatMap(sale => sale))
    })
    const buttonCss = ' flex md:w-56 p-4 rounded-2xl items-center text-sm justify-around';
    const chartOptions = [
        { value: 'Line', label: 'Line chart' },
        { value: 'Bar', label: 'Bar chart' },
        { value: 'Area', label: 'Area chart' },
        { value: 'Pie', label: 'Pie chart' }
    ]
    const intervalOptions = [
        { value: 'Years', label: 'Years' },
        { value: 'Months', label: 'Months' },
    ]
    const divideOptions = [
        { value: 'None', label: 'None' },
        { value: 'Branches', label: 'Branches' },
        { value: 'Services', label: 'Services' }
    ]
    const yearFromOptions = [
        { value: '2015', label: '2015' },
        { value: '2016', label: '2016' },
        { value: '2017', label: '2017' },
        { value: '2018', label: '2018' },
        { value: '2019', label: '2019' },
        { value: '2020', label: '2020' },
        { value: '2021', label: '2021' },
        { value: '2022', label: '2022' },
        { value: '2023', label: '2023' }
    ]
    const yearToOptions = [
        { value: '2015', label: '2015' },
        { value: '2016', label: '2016' },
        { value: '2017', label: '2017' },
        { value: '2018', label: '2018' },
        { value: '2019', label: '2019' },
        { value: '2020', label: '2020' },
        { value: '2021', label: '2021' },
        { value: '2022', label: '2022' },
        { value: '2023', label: '2023' }
    ]
    const customStyles = {
        control: (provided, state) => ({
            ...provided,
            background: '#fff',
            borderColor: '#9e9e9e',
            minHeight: '30px',
            height: '30px',
            boxShadow: state.isFocused ? null : null,
        }),

        valueContainer: (provided, state) => ({
            ...provided,
            height: '30px',
            padding: '0 6px'
        }),

        input: (provided, state) => ({
            ...provided,
            margin: '0px',
        }),
        indicatorSeparator: state => ({
            display: 'none',
        }),
        indicatorsContainer: (provided, state) => ({
            ...provided,
            height: '30px',
        }),
    };
    const handleShowChart = (e, i) => {
        setLoading(true);
        setActiveChart(true);
        dispatch(fetchData({ start: filter.startYear, end: filter.endYear, divider: divider, interval: interval }));
        dispatch(changeInterval(interval));
        dispatch(changeDivide(divider));
        switch (divider) {
            case 'Branches':
                setDividers(branches);
                break
            case 'Services':
                setDividers(services);
                break
            default:
                setDividers([]);
        }
    };
    const handleInputFilter = (e, i) => {
        if (chartOptions.includes(e)) {
            dispatch(changeChartType(e.value));
        }
        if (yearFromOptions.includes(e)) {
            dispatch(changeStart(e.value));
        }
        if (yearToOptions.includes(e)) {
            dispatch(changeEnd(e.value));
        }
        if (intervalOptions.includes(e)) {
            setInterval(e.value);
        }
        if (divideOptions.includes(e)) {
            setDivider(e.value);
        }

    }

    return (
        <div className='m-4  md:m-10 mt-24 p-10 gap-10 bg-white dark:bg-secondary-dark-bg rounded-3xl'>
            <div className="flex justify-around relative">
                <div className={`bg-green-50 ${buttonCss} `}>Chart type:<Select options={chartOptions} styles={customStyles} onChange={(e, i) => { handleInputFilter(e, i) }} /></div>
                <div className={`bg-green-50 ${buttonCss}`}>From:<Select options={yearFromOptions} styles={customStyles} onChange={(e, i) => { handleInputFilter(e, i) }} /></div>
                <div className={`bg-green-50 ${buttonCss}`}>To:<Select options={yearToOptions} styles={customStyles} onChange={(e, i) => { handleInputFilter(e, i) }} /></div>
                <div className={`bg-green-50 ${buttonCss}`}>Interval:<Select options={intervalOptions} styles={customStyles} onChange={(e, i) => { handleInputFilter(e, i) }} /></div>
                <div className={`bg-green-50 ${buttonCss}`}>Divide:<Select options={divideOptions} styles={customStyles} onChange={(e, i) => { handleInputFilter(e, i) }} /></div>
            </div>
            <div className='h-16 mt-2 flex align-middle'>
                <button
                    id='conten-show-chart'
                    className='w-150 bg-blue-300 p-4 m-auto'
                    type='button'
                    onClick={(e) => { handleShowChart(e) }}
                >
                    Show chart</button>
            </div>
            <div>
                {loading ?
                    (<MoonLoader
                        color={color}
                        loading={loading}
                        cssOverride={{
                            display: "block",
                            margin: "0 auto",
                        }}
                        size={50}
                        aria-label="Loading Spinner"
                        data-testid="loader"
                    />) : (<div>
                        {(activeChart && filter.chartType == 'Line') && <Line
                            data={dataSource1}
                            dividers={dividers}
                            timeInterval={interval}
                            // valueInterval={interval=='Years'? 500 : 200}
                            // minValue={interval=='Years'? 10000 : 0} 
                            />}
                        {(activeChart && filter.chartType == 'Pie') && <Pie />}
                        {(activeChart && filter.chartType == 'Area') && <Area />}
                        {(activeChart && filter.chartType == 'Bar') && <Bar />}</div>)
                }
            </div>


        </div>
    )
}

export default Sales