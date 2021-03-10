import CssBaseline from "@material-ui/core/CssBaseline";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import Cookies from "js-cookie";
import Layout from "./components/Layout/Layout";
import RegistrationContainer from "./containers/RegistrationContainer/RegistrationContainer";
import LoginContainer from "./containers/LoginContainer/LoginContainer";

/**
 * Main function of the application
 */

const getSession = () => {
	const jwt = Cookies.get("Authorization");
	// console.log(jwt);
	// Cookies.set("foo", "bar");
	console.log(jwt);
	let session;
	try {
		if (jwt) {
			const base64Url = jwt.split(".")[1];
			const base64 = base64Url.replace("-", "+").replace("_", "/");
			// what is window.atob ?
			// https://developer.mozilla.org/en-US/docs/Web/API/WindowOrWorkerGlobalScope/atob
			session = JSON.parse(window.atob(base64));
		}
	} catch (error) {
		console.log(error);
	}
	return session;
};

function App() {
	console.log(getSession());
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
