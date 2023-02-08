import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import axios from "axios";
import jwt_decode from "jwt-decode";

const initialState = {
    revenueData: [],
    services:[],
    branches:[],
    status:'idle',

}

const revenueSlice = createSlice({
    name: 'revenue',
    initialState,
    reducers:{
        
    },
    extraReducers(builder){
        builder.addCase(fetchData.pending, (state,action)=>{
            state.status = 'pending';
        })
        .addCase(fetchData.fulfilled,(state,action)=>{
            console.log('action',action);
            state.revenueData = action.payload.sales;
            state.services = action.payload.services;
            state.branches = action.payload.branches;
            state.status = 'fulfilled';
        })
        .addCase(fetchData.rejected,(state,action)=>{
            state.status = 'rejected';
        })
    }
    
})

export const fetchData = createAsyncThunk('revenue/fetchData',async (req)=>{
    let salesUrl = '';
    const token = sessionStorage.getItem('id_token');
    const decode = jwt_decode(token)
    console.log('decode',decode)
    const headers = new Headers();
    // headers.set('Content-type', 'plain/text');
    // headers.set('Authorization', `Bearer ${token}`);
    // headers.set('Access-Control-Allow-Origin','*')
    console.log(req.divider)
    switch(req.divider){
        case 'Services':
            salesUrl = `http://localhost:8082/api/sales/service?start=${req.start}&end=${req.end}`
            break;
        case 'Branches':
            salesUrl = `http://localhost:8082/api/sales/branches?start=${req.start}&end=${req.end}`
            break;
        default:
            salesUrl = `http://localhost:8082/api/sales/all?start=${req.start}&end=${req.end}`
    }
    
    let serviceUrl = 'http://localhost:8082/api/service/all'
    let branchUrl = 'http://localhost:8082/api/branch/all'
    console.log('url:' ,salesUrl)
    const sale = await axios.get(salesUrl,{
        headers: {
            'Content-Type': 'plain/text',
            'Authorization': 'Bearer '+token
        },
    });
    const services = await axios.get(serviceUrl,{
        headers: {
            'Content-Type': 'plain/text',
            'Authorization': 'Bearer '+token
        },
    });
    const branches =  await axios.get(branchUrl,{
        headers: {
            'Content-Type': 'plain/text',
            'Authorization': 'Bearer '+token
        },
    });
    return {
        sales: sale.data,
        services: services.data,
        branches: branches.data
    };
})


export default revenueSlice.reducer;