import { createSelector } from '@reduxjs/toolkit';

export const revenueDataSelector = (state) => state.revenue.revenueData;
export const servicesSlector = (state) => state.revenue.services;
export const branchesSlector = (state) => state.revenue.branches;
export const statusSlector = (state) => state.revenue.status;
export const filterStartDaySelector = (state) => state.filter.startYear;
export const filterEndDaySelector = (state) => state.filter.endYear;
export const filterIntervalSelector = (state) => state.filter.interval;
export const filterDivideSelector = (state) => state.filter.divide;

export const lastRevenueDataSelector = createSelector(
    revenueDataSelector,
    branchesSlector,
    servicesSlector,
    filterStartDaySelector,
    filterEndDaySelector,
    filterIntervalSelector,
    filterDivideSelector,
    statusSlector,
    (data, branches, services, start, end, interval, divide,status) => {
        var returnData = [];
        console.log('data:', data)
        if(status == 'pending'){
            return returnData;
        }
        switch (divide) {
            case 'Services':
                if (interval == 'Years') {
                    data.forEach(service => {
                        console.log('service:', service)
                        let serviceSale = []
                        service.forEach(yearsSale => {
                            let reduce = [];
                            yearsSale.monthSaleList.forEach(monthSale => { reduce.push(monthSale.saleList.reduce((acc, curr) => acc + curr.saleAmount, 0)) })
                            let a = {
                                x: new Date(yearsSale.year, 0, 1),
                                y: reduce.reduce((acc, curr) => acc + curr),
                            }
                            serviceSale.push(a)
                        })
                        returnData.push(serviceSale)
                    });
                } else {
                    data.forEach(service => {
                        console.log('service:', service)
                        let serviceSale = []
                        service.forEach(yearsSale => {
                            yearsSale.monthSaleList.forEach(monthSale => {
                                let a = {
                                    x: new Date(yearsSale.year, monthSale.month, 1),
                                    y: monthSale.saleList.reduce((acc, curr) => acc + curr.saleAmount, 0),
                                }
                                serviceSale.push(a)
                            })
                        })
                        returnData.push(serviceSale)
                     });
                }
                break;
            case 'Branches':
                if (interval == 'Years') {
                    data.forEach(branch => {
                        console.log('branch:', branch)
                        let branchSale = []
                        branch.forEach(yearsSale => {
                            let reduce = [];
                            yearsSale.monthSaleList.forEach(monthSale => { reduce.push(monthSale.saleList.reduce((acc, curr) => acc + curr.saleAmount, 0)) })
                            let a = {
                                x: new Date(yearsSale.year, 0, 1),
                                y: reduce.reduce((acc, curr) => acc + curr),
                            }
                            branchSale.push(a)
                        })
                        returnData.push(branchSale)
                    });
                } else {
                    data.forEach(branch => {
                        console.log('branch:', branch)
                        let branchSale = []
                        branch.forEach(yearsSale => {
                            yearsSale.monthSaleList.forEach(monthSale => {
                                let a = {
                                    x: new Date(yearsSale.year, monthSale.month, 1),
                                    y: monthSale.saleList.reduce((acc, curr) => acc + curr.saleAmount, 0),
                                }
                                branchSale.push(a)
                            })
                        })
                        returnData.push(branchSale)
                    });
                }
                break;
            default:
                let arr = [];
                if (interval == 'Years') {
                    data.forEach(yearsSale => {
                        console.log('all sales:', yearsSale)
                        let totalSale = []
                            let reduce = [];
                            yearsSale.monthSaleList.forEach(monthSale => { reduce.push(monthSale.saleList.reduce((acc, curr) => acc + curr.saleAmount, 0)) })
                            let a = {
                                x: new Date(yearsSale.year, 0, 1),
                                y: reduce.reduce((acc, curr) => acc + curr),
                            }
                            totalSale.push(a)
                        arr.push(totalSale)
                    });
                    returnData.push(arr);
                } else {
                    data.forEach(yearsSale => {
                        console.log('all sales:', yearsSale)
                        let totalSale = []
                            yearsSale.monthSaleList.forEach(monthSale => {
                                let a = {
                                    x: new Date(yearsSale.year, monthSale.month, 1),
                                    y: monthSale.saleList.reduce((acc, curr) => acc + curr.saleAmount, 0),
                                }
                                totalSale.push(a)
                            })
                            arr.push(totalSale)
                    });
                    returnData.push(arr);
                }
                break;
        }
        return returnData;
    }
)