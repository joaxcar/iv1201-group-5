import { useState } from "react";
import CssBaseline from "@material-ui/core/CssBaseline";
import {
	BrowserRouter as Router,
	Switch,
	Route,
	Link,
	Redirect,
} from "react-router-dom";
import Layout from "./components/Layout/Layout";
import RegistrationContainer from "./containers/RegistrationContainer/RegistrationContainer";
import LoginContainer from "./containers/LoginContainer/LoginContainer";
import ProfileContainer from "./containers/ProfileContainer/ProfileContainer";

/**
 * Main function of the application
 */
function App() {
	const [signedInState, setSignedInState] = useState({
		isSignedIn: false,
		accountId: null,
	});

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
						<Route path="/profile">
							{signedInState.isSignedIn ? (
								<ProfileContainer />
							) : (
								<Redirect to="/login" />
							)}
						</Route>
					</Switch>
				</Layout>
			</Router>
		</>
	);
}

export default App;
