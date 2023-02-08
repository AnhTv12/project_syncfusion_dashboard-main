import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import axios from "axios";
import jwt_decode from "jwt-decode";
const initialState = {
    username: '',
    role: '',
    anthenticated: false,
    active: false,
    status:'idle',
    data: []
};

const userrSlice = createSlice({
    name: 'user',
    initialState,
    reducers: {
        authenticate:{
            reducer(state,action){
                state.anthenticated = true;
            }
        },
        logout:{
            reducer(state,action){
                state.username = '';
                state.role = '';
                state.anthenticated = false;
                state.active = false;
                state.status = 'idle';
            }
        }
    },
    extraReducers(builder){
        builder.addCase(updateLogin.pending,(state,action)=>{
            state.status = 'pending';
        })
        .addCase(updateLogin.fulfilled,(state,action)=>{
            console.log('role payload', action.payload);
            state.role = action.payload;
            if(action.payload == 'admin'){
                state.active = true;
            }
            state.status = 'idle'
        })
        .addCase(fetchUserData.pending,(state,action)=>{
            console.log('status befor:',state.status)
            state.status = 'pending';
            console.log('status after:',state.status)
        })
        .addCase(fetchUserData.fulfilled,(state,action)=>{
            console.log('status last:',state.status)
            console.log('payload', action.payload);
            state.data = action.payload;
            state.status = 'idle'
        })
    }
}
)

export const updateLogin = createAsyncThunk('user/updateLogin',async ()=>{
    const token = sessionStorage.getItem('id_token');
    console.log('token session', token)
    const decode = jwt_decode(token);
    console.log('token decode', decode)
    let username = decode.sub
    const res = await axios.get(`http://localhost:8081/user/role/${username}`,{
        headers: {
            'Content-Type': 'plain/text',
            'Authorization': 'Bearer '+token
        },
    });
    const name = res.data
    return name;

})

export const fetchUserData = createAsyncThunk('user/data',async ()=>{
    const token = sessionStorage.getItem('id_token');
    console.log('token session', token)
    const res = await axios.get(`http://localhost:8082/api/customer/all`,{
        headers: {
            'Content-Type': 'plain/text',
            'Authorization': 'Bearer '+token
        },
    });

    return res.data;

})

export const { authenticate,logout } = userrSlice.actions;

export default userrSlice.reducer;