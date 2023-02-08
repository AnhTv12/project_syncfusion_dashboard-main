import React from 'react';
import { MdOutlineCancel } from 'react-icons/md';
import { useDispatch, useSelector } from 'react-redux';
import { Button } from '.';
import { userProfileData } from '../data/dummy';
import { useStateContext } from '../contexts/ContextProvider';
import avatar from '../data/avatar.jpg';
import avatar11 from '../data/avatar11.jpg';
import { logout } from '../app/user/userSlice';
import { useNavigate } from 'react-router-dom';

const UserProfile = () => {
  const dispatch = useDispatch();
  const { currentColor } = useStateContext();
  const isAuthenticated = useSelector((state)=>state.user.anthenticated);
  const navigate = useNavigate()
  const handleFuntion = async ()=> {
    if(isAuthenticated){
        localStorage.clear();
        sessionStorage.clear();
        dispatch(logout());
        window.location.href = 'http://localhost:8081/logout'

    }else{
      window.location.href = 'http://localhost:3000/login'
    }
  }
  return (
    <div className="nav-item absolute right-1 top-16 bg-white dark:bg-[#42464D] p-8 rounded-lg w-96">
      <div className="flex justify-between items-center">
        <p className="font-semibold text-lg dark:text-gray-200">User Profile</p>
        <Button
          icon={<MdOutlineCancel />}
          color="rgb(153, 171, 180)"
          bgHoverColor="light-gray"
          size="2xl"
          borderRadius="50%"
        />
      </div>
      <div className="flex gap-5 items-center mt-6 border-color border-b-1 pb-6">
        <img
          className="rounded-full h-24 w-24"
          src={isAuthenticated? avatar:avatar11}
          alt="user-profile"
        />
        <div>
          <p className="font-semibold text-xl dark:text-gray-200"> {isAuthenticated?'Michael Roberts':'Guest' } </p>
          <p className="text-gray-500 text-sm dark:text-gray-400">  {isAuthenticated?'Administrator':'User' }   </p>
          <p className="text-gray-500 text-sm font-semibold dark:text-gray-400"> info@shop.com </p>
        </div>
      </div>
      {isAuthenticated&&<div>
        {userProfileData.map((item, index) => (
          <div key={index} className="flex gap-5 border-b-1 border-color p-4 hover:bg-light-gray cursor-pointer  dark:hover:bg-[#42464D]">
            <button
              type="button"
              style={{ color: item.iconColor, backgroundColor: item.iconBg }}
              className=" text-xl rounded-lg p-3 hover:bg-light-gray"
            >
              {item.icon}
            </button>

            <div>
              <p className="font-semibold dark:text-gray-200 ">{item.title}</p>
              <p className="text-gray-500 text-sm dark:text-gray-400"> {item.desc} </p>
            </div>
          </div>
        ))}
      </div>}
      <div className="mt-5">
        <Button
          color="white"
          bgColor={currentColor}
          text={isAuthenticated?'Logout':'Login'}
          borderRadius="10px"
          width="full"
          handleFuntion = {handleFuntion}
        />
      </div>
    </div>

  );
};

export default UserProfile;
