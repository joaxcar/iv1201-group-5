import CssBaseline from "@material-ui/core/CssBaseline";
import Layout from "./components/Layout/Layout";
import RegistrationContainer from "./containers/RegistrationContainer/RegistrationContainer";

/**
 * Main function of the application
 */
function App() {
	return (
		<>
			<CssBaseline />
			<Layout>
				<RegistrationContainer />
			</Layout>
		</>
	);
}

export default App;
