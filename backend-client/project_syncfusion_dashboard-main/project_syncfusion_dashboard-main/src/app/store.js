import { configureStore } from '@reduxjs/toolkit'
import revenueSlice from './revenua/revenueSlice'
import filterSlice from './revenua/filterSlice'
import userSlice from './user/userSlice'

export const store = configureStore({
  reducer: {
    revenue: revenueSlice,
    filter: filterSlice,
    user: userSlice,
  },
})