import React from 'react';
import { Route } from 'react-router';
import BundlesPage from './Pages/BundlesPage';
import Home from './Pages/Home';

const App = () => {
  return (
    <div>
      <Route path="/" component={Home} exact={true} />
      <Route path="/bundles" component={BundlesPage} />
    </div>
  ) 
}

export default App;