import React, { useState, useEffect } from "react";
import CssBaseline from "@material-ui/core/CssBaseline";
import { Switch, Route, Link, Redirect, useHistory } from "react-router-dom";
import Layout from "./components/Layout/Layout";
import RegistrationContainer from "./containers/RegistrationContainer/RegistrationContainer";
import LoginContainer from "./containers/LoginContainer/LoginContainer";
import ProfileContainer from "./containers/ProfileContainer/ProfileContainer";
import Alert from "./components/Alert/Alert";

const AlertContext = React.createContext();
const UserContext = React.createContext();
export { AlertContext, UserContext };

/**
 * Main function of the application
 */
function App() {
	const [signedInState, setSignedInState] = useState({
		isSignedIn: false,
		accountId: null,
	});

	const [alert, setAlert] = useState({ open: false, type: "", message: "" });

	function showAlert(type, message) {
		setAlert({ open: true, type, message });
		setTimeout(() => setAlert({ ...alert, open: false }), 7000);
	}

	function logout() {
		setSignedInState({ isSignedIn: false, accountId: null });
	}

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
			<AlertContext.Provider value={showAlert}>
				<UserContext.Provider value={{ signedInState, logout }}>
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
				</UserContext.Provider>
			</AlertContext.Provider>
			{alert.open ? (
				<Alert type={alert.type} message={alert.message} />
			) : null}
		</>
	);
}

export default App;
