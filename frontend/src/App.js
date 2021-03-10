import { useState, useEffect } from "react";
import CssBaseline from "@material-ui/core/CssBaseline";
import { Switch, Route, Link, Redirect, useHistory } from "react-router-dom";
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

	let history = useHistory();

	useEffect(() => {
		if (signedInState.isSignedIn) {
			history.push("/profile");
		}
	});

	function onLogin(accountId) {
		setSignedInState({ isSignedIn: true, accountId });
	}

	return (
		<>
			<CssBaseline />

			<Layout>
				<Switch>
					<Route exact path="/">
						<RegistrationContainer />
					</Route>
					<Route path="/login">
						<LoginContainer onLogin={onLogin} />
					</Route>
					<Route path="/register">
						<RegistrationContainer />
					</Route>
					<Route path="/profile">
						{signedInState.isSignedIn ? (
							<ProfileContainer
								accountId={signedInState.accountId}
							/>
						) : (
							<Redirect to="/login" />
						)}
					</Route>
				</Switch>
			</Layout>
		</>
	);
}

export default App;
