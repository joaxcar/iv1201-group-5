import CssBaseline from "@material-ui/core/CssBaseline";
import Layout from "./components/Layout/Layout";
import RegistrationContainer from "./containers/RegistrationContainer/RegistrationContainer";
import LoginContainer from "./containers/LoginContainer/LoginContainer";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";

/**
 * Main function of the application
 */
function App() {
	return (
		<>
			<CssBaseline />
			<Router>
				<Layout>
					<Switch>
						<Route exact path="/">
							<RegistrationContainer />
						</Route>
						<Route path="/login">
							<LoginContainer />
						</Route>
						<Route path="/register">
							<RegistrationContainer />
						</Route>
					</Switch>
				</Layout>
			</Router>
		</>
	);
}

export default App;
