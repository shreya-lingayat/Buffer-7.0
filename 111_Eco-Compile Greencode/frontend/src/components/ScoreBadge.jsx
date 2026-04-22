import React from 'react';
import { motion } from 'framer-motion';

const ScoreBadge = ({ score, grade }) => {
  const getColor = (grade) => {
    if (grade.startsWith('A')) return 'text-emerald-400 border-emerald-500/20 bg-emerald-500/5';
    if (grade.startsWith('B')) return 'text-blue-400 border-blue-500/20 bg-blue-500/5';
    if (grade.startsWith('C')) return 'text-yellow-400 border-yellow-500/20 bg-yellow-500/5';
    return 'text-red-400 border-red-500/20 bg-red-500/5';
  };

  return (
    <motion.div 
      initial={{ scale: 0.8, opacity: 0 }}
      animate={{ scale: 1, opacity: 1 }}
      className={`relative w-48 h-48 rounded-full border-4 flex flex-col items-center justify-center ${getColor(grade)}`}
    >
      <div className="absolute inset-0 rounded-full bg-current opacity-5 blur-2xl animate-pulse" />
      <span className="text-sm font-medium uppercase tracking-widest opacity-60">Green Score</span>
      <span className="text-6xl font-black mt-1 mb-1">{score}</span>
      <div className={`px-4 py-1 rounded-full border text-sm font-bold bg-white/10`}>
        GRADE {grade}
      </div>
    </motion.div>
  );
};

export default ScoreBadge;
