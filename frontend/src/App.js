import CssBaseline from "@material-ui/core/CssBaseline";
import MomentUtils from "@date-io/moment";
import { MuiPickersUtilsProvider } from "@material-ui/pickers";

import RegistrationContainer from "./containers/RegistrationContainer/RegistrationContainer";

function App() {
	return (
		<>
			<CssBaseline />
			<MuiPickersUtilsProvider utils={MomentUtils}>
				<RegistrationContainer />
			</MuiPickersUtilsProvider>
		</>
	);
}

export default App;
