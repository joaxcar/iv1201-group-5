import CssBaseline from "@material-ui/core/CssBaseline";
import Layout from "./components/Layout/Layout";
import RegistrationContainer from "./containers/RegistrationContainer/RegistrationContainer";
import LoginContainer from "./containers/LoginContainer/LoginContainer";

/**
 * Main function of the application
 */
function App() {
	return (
		<>
			<CssBaseline />
			<Layout>
				{/* <RegistrationContainer /> */}
				<LoginContainer />
			</Layout>
		</>
	);
}

export default App;
