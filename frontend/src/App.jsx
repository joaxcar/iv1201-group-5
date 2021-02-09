import CssBaseline from "@material-ui/core/CssBaseline";
import MomentUtils from "@date-io/moment";
import { MuiPickersUtilsProvider } from "@material-ui/pickers";

import Registration from "./Registration";

function App() {
	return (
		<>
			<CssBaseline />
			<MuiPickersUtilsProvider utils={MomentUtils}>
				<Registration />
			</MuiPickersUtilsProvider>
		</>
	);
}

export default App;
