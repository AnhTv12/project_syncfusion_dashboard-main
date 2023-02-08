import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";

const initialState = {
    startYear: 1996,
    endYear: 2005,
    interval: 'None',
    divide: 'None',
    chartType: 'Line',
};

const filterSlice = createSlice({
    name: 'filter',
    initialState,
    reducers:{
        changeStart:{
            reducer(state,action){
                state.startYear = action.payload;
            }
        },
        changeEnd:{
            reducer(state,action){
                state.endYear = action.payload;
            }
        },
        changeInterval:{
            reducer(state,action){
                state.interval = action.payload;
            }
        },
        changeDivide:{
            reducer(state,action){
                state.divide = action.payload;
            }
        },
        changeChartType:{
            reducer(state,action){
                state.chartType = action.payload;
            }
        }
    }
}
)

export const {changeChartType,changeStart, changeEnd,changeInterval,changeDivide} = filterSlice.actions;

export default filterSlice.reducer;