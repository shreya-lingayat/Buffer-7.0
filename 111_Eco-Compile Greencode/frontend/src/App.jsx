import React, { useState } from 'react';
import { motion, AnimatePresence } from 'framer-motion';
import { Leaf, Globe, Zap, History, FileCode } from 'lucide-react';
import useAnalysis from './hooks/useAnalysis';
import FileUploadZone from './components/FileUploadZone';
import ScoreBadge from './components/ScoreBadge';
import SmellList from './components/SmellList';
import RefactoringAdvice from './components/RefactoringAdvice';
import StructuralGraph from './components/StructuralGraph';

function App() {
  const { analyzeFile, data, loading, error } = useAnalysis();
  const [selectedSmellIndex, setSelectedSmellIndex] = useState(null);

  const handleFileSelect = (file) => {
    setSelectedSmellIndex(null);
    analyzeFile(file);
  };

  const getSuggestion = () => {
    if (selectedSmellIndex === null || !data) return null;
    const smell = data.smells[selectedSmellIndex];
    return data.suggestions[smell.suggestionKey];
  };

  return (
    <div className="min-h-screen w-full flex flex-col items-center p-8">
      {/* Header */}
      <header className="w-full max-w-7xl flex justify-between items-center mb-16">
        <div className="flex items-center space-x-3">
          <div className="w-10 h-10 bg-emerald-500 rounded-xl flex items-center justify-center shadow-lg shadow-emerald-500/20">
            <Leaf className="text-white w-6 h-6" />
          </div>
          <h1 className="text-2xl font-black tracking-tighter uppercase italic">
            Green<span className="text-emerald-500">Code</span>
          </h1>
        </div>
        <nav className="flex space-x-8 text-sm font-medium text-slate-400">
          <a href="#" className="hover:text-emerald-400 transition-colors flex items-center"><History className="w-4 h-4 mr-1"/> History</a>
          <a href="#" className="hover:text-emerald-400 transition-colors flex items-center"><Globe className="w-4 h-4 mr-1"/> Global Impact</a>
        </nav>
      </header>

      <main className="w-full max-w-7xl flex flex-col items-center">
        <AnimatePresence mode="wait">
          {!data && !loading ? (
            <FileUploadZone onFileSelect={handleFileSelect} loading={loading} />
          ) : data ? (
            <motion.div 
              initial={{ opacity: 0 }}
              animate={{ opacity: 1 }}
              className="w-full flex flex-col space-y-8"
            >
              {/* Dashboard Grid */}
              <div className="grid grid-cols-1 lg:grid-cols-3 gap-8 w-full">
                {/* Score Panel */}
                <div className="lg:col-span-1 glass p-8 rounded-3xl flex flex-col items-center justify-center space-y-6">
                  <ScoreBadge score={data.score} grade={data.grade} />
                  <div className="text-center">
                    <h2 className="text-3xl font-bold mb-2">Ecological Report</h2>
                    <p className="text-slate-400 text-sm">Your code's total environmental footprint results are calculated.</p>
                  </div>
                  <div className="grid grid-cols-2 gap-4 w-full">
                    <div className="bg-white/5 p-4 rounded-2xl border border-white/5">
                      <Zap className="text-yellow-400 w-5 h-5 mb-2" />
                      <p className="text-[10px] text-slate-500 uppercase tracking-widest font-bold">Total Severity</p>
                      <p className="text-xl font-bold">{data.smells.reduce((acc, s) => acc + s.severity, 0)}</p>
                    </div>
                    <div className="bg-white/5 p-4 rounded-2xl border border-white/5">
                      <Globe className="text-blue-400 w-5 h-5 mb-2" />
                      <p className="text-[10px] text-slate-500 uppercase tracking-widest font-bold">Est. CO2 Saving</p>
                      <p className="text-xl font-bold">{data.co2EstimateGrams}g<span className="text-xs text-slate-500 font-normal">/yr</span></p>
                    </div>
                  </div>
                  <button 
                    onClick={() => window.location.reload()}
                    className="w-full py-3 rounded-xl bg-emerald-500 hover:bg-emerald-400 text-white font-bold transition-all shadow-lg shadow-emerald-500/20 text-sm flex items-center justify-center"
                  >
                    <FileCode className="w-4 h-4 mr-2" /> Analyze New File
                  </button>
                </div>

                {/* Analysis/Graph Panel */}
                <div className="lg:col-span-2 space-y-8">
                  <StructuralGraph smells={data.smells} />
                  <div className="grid grid-cols-1 md:grid-cols-2 gap-8 h-full">
                    <div className="h-full">
                      <SmellList 
                        smells={data.smells} 
                        onSelect={setSelectedSmellIndex} 
                        selectedIndex={selectedSmellIndex} 
                      />
                    </div>
                    <div className="h-[432px]">
                      <RefactoringAdvice suggestion={getSuggestion()} />
                    </div>
                  </div>
                </div>
              </div>
            </motion.div>
          ) : (
             <div className="flex flex-col items-center py-20">
                <Loader2 className="w-12 h-12 text-emerald-500 animate-spin mb-4" />
                <p className="text-emerald-100 text-lg font-medium">Crunching the Tree...</p>
              </div>
          )}
        </AnimatePresence>
      </main>

      {error && (
        <motion.div 
          initial={{ y: 100 }}
          animate={{ y: 0 }}
          className="fixed bottom-8 bg-red-500 text-white px-6 py-3 rounded-xl shadow-2xl font-bold flex items-center"
        >
          <AlertTriangle className="w-5 h-5 mr-3" />
          {error}
        </motion.div>
      )}
    </div>
  );
}

// Helper imports for error display
import { AlertTriangle, Loader2 } from 'lucide-react';

export default App;
