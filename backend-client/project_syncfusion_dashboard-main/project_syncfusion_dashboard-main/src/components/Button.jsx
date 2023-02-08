import React from 'react';

import { useStateContext } from '../contexts/ContextProvider';

const Button = ({ icon, bgColor, color, bgHoverColor, size, text, borderRadius, width, handleFuntion }) => {
  const { setIsClicked, initialState } = useStateContext();
  const handleOnClick = (e)=>{
    setIsClicked(initialState);
    if(handleFuntion!=null){
      handleFuntion();
    }
  }

  return (
    <button
      type="button"
      onClick={(e) => handleOnClick(e)}
      style={{ backgroundColor: bgColor, color, borderRadius }}
      className={` text-${size} p-3 w-${width} hover:drop-shadow-xl hover:bg-${bgHoverColor}`}
    >
      {icon} {text}
    </button>
  );
};

export default Button;
