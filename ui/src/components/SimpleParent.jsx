import React from 'react';
import Simple from './Simple';

function SimpleParent() {
  return (
    <div>
      <Simple aProperty="some value" />
      <Simple aProperty="some other value" />
    </div>
  );
}

export default SimpleParent;
